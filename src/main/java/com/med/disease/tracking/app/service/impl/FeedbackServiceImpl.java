package com.med.disease.tracking.app.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dao.UserInfoDAO;
import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.EPassMapper;
import com.med.disease.tracking.app.mapper.FetchFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitFeedbackMapper;
import com.med.disease.tracking.app.mapper.UserMapper;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.model.UserRisk;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.CommonUtil;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	private static Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private SubmitFeedbackMapper submitFeedbackMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EPassMapper ePassMapper;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	UserInfoDAO userDAO;
	
	@Autowired
	EPassDAO ePassDAO;

	@Autowired
	private FetchFeedbackMapper fetchFeedbackMapper;

	@Override
	@Transactional
	public void submitFeedback(FeedbackRequestDTO requestDTO) throws Exception {
		Object object = submitFeedbackMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List feedbackList = new ArrayList((Collection<?>) object);
		if (!feedbackList.isEmpty()) {
			feedbackDAO.deleteFeedback((Feedback) feedbackList.stream().findFirst().get());
			for (Object feedback : feedbackList) {
				if (feedbackDAO.submitFeedback((Feedback) feedback) <= 0) {
					LOGGER.error("Unable to submit Feedback");
					throw new CovidAppException("Submit feedback failed");
				}
			}
		}
		
		if (Constant.RiskStatus.H.equals(commonUtil.getUserRiskStatus(requestDTO.getUserId(), requestDTO.getSurveyId()))
				&& commonUtil.isCurrentlyEpassAllowed(requestDTO.getUserId(), requestDTO.getSurveyId())) {
			User user = new User();
			user.setUserId(requestDTO.getUserId());

			Survey survey = new Survey();
			survey.setSurveyId(requestDTO.getSurveyId());

			User createdBy = new User();
			createdBy.setUserId(requestDTO.getUserId());

			EPass ePass = new EPass();
			ePass.setUser(user);
			ePass.setSurvey(survey);
			ePass.setIsAllowed(false);
			ePass.setToDate(LocalDate.now());
			ePass.setCreatedBy(createdBy);
			ePassDAO.submitEPass(ePass);
		}
	}

	@Override
	@Transactional
	public FeedbackDTO fetchFeedbacks(FetchFeedbackRequestDTO fetchFeedbackRequestDTO) throws Exception {
		Feedback feedback = (Feedback) fetchFeedbackMapper.map(fetchFeedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN,
				null);
		List<Feedback> feedbackList = feedbackDAO.getFeedbacks(feedback);
		return (FeedbackDTO) fetchFeedbackMapper.map(feedbackList, MappingTypeEnum.MAPTORESPONSE, null);
	}

	@Override
	@Transactional
	public SurveyFeedbackDTO fetchSurveyFeedbacks(FetchFeedbackRequestDTO requestDTO) throws Exception {
		User reqUser = new User();
		reqUser.setUserId(requestDTO.getUserId());
		
		// get manager info
		User manager = getUserInfo(reqUser);
		
		// get users under manager
		User userToSearch = new User();
		userToSearch.setMgrID(manager.getUserId());
		List<User> derivedUsers = userDAO.searchUser(userToSearch);
		
		// calculate/apply riskstatus
		return (CollectionUtils.isEmpty(derivedUsers)) ? null
				: caculateRisk(derivedUsers, requestDTO.getSurveyId(),
						(UserDTO) userMapper.map(manager, MappingTypeEnum.MAPTORESPONSE, null));
	}
	
	public User getUserInfo(User user) throws Exception{
		List<User> inputUser = userDAO.searchUser(user);
		if (ObjectUtils.isEmpty(inputUser)) {
			LOGGER.error("User Not Found");
			throw new CovidAppException("User Not Found");
		}
		return inputUser.get(0);
	}
	
	public List<User> getUsers(User user) throws Exception{
		List<User> inputUser = userDAO.searchUser(user);
		if (ObjectUtils.isEmpty(inputUser)) {
			LOGGER.error("Users Not Found");
			throw new CovidAppException("Users Not Found");
		}
		return inputUser;
	}
	
	public SurveyFeedbackDTO caculateRisk(List<User> derivedUsers, Integer surveyId, UserDTO manager) throws Exception {
		SurveyFeedbackDTO surveyFeedback = new SurveyFeedbackDTO();
		surveyFeedback.setManager(manager);
		
		UserRisk userRiskIn = new UserRisk();
		userRiskIn.setManagerId(manager.getUserId());
		userRiskIn.setSurveyId(surveyId);
		
		List<UserRisk> riskStates = feedbackDAO.getUserRisks(userRiskIn);
		
		// fetch epass detail
		User userToSearch = new User();
		userToSearch.setUserId(manager.getMgrID());

		Survey surveyToSearch = new Survey();
		surveyToSearch.setSurveyId(surveyId);

		EPass ePassToSearch = new EPass();
		ePassToSearch.setUser(userToSearch);
		ePassToSearch.setSurvey(surveyToSearch);

		List<EPass> ePassUser = ePassDAO.searchUser(ePassToSearch);
		
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User usr : derivedUsers) {
			UserDTO usrObj = (UserDTO) userMapper.map(usr, MappingTypeEnum.MAPTORESPONSE, null);
			Optional<EPass> ePassOp = ePassUser.stream().filter(x -> x.getUser() != null && x.getUser().getUserId().equals(usr.getUserId())).findAny();
			
			EPass ePass = ePassOp.isPresent() ? ePassOp.get() : null;
			usrObj.setEpass((EPassDTO) ePassMapper.map(ePass, MappingTypeEnum.MAPTORESPONSE, null));
			userDTOs.add(usrObj);
		}
		userDTOs.stream().forEach(usr -> {
			usr.setRiskStatus(CommonUtil.getRiskStatus(riskStates, usr.getUserId()));
		});
		surveyFeedback.setUsers(userDTOs);
		return surveyFeedback;
	}

	@Override
	public SurveyReportDTO fetchAllSurveyFeedbacks(FetchFeedbackRequestDTO requestDTO) throws Exception {
		// fetch all managers
		User searchMgrs = new User();
		searchMgrs.setRole(Constant.Role.MANAGER);
		List<User> managers = getUsers(searchMgrs);
		
		if(ObjectUtils.isEmpty(managers))
			return null;
		
		SurveyReportDTO surveyReportDTO = new SurveyReportDTO();
		List<SurveyFeedbackDTO> feedbacks = new ArrayList<>();
		
		for(User manager : managers) {
			// get users under manager
			User userToSearch = new User();
			userToSearch.setMgrID(manager.getUserId());
			List<User> derivedUsers = userDAO.searchUser(userToSearch);
			
			if(CollectionUtils.isEmpty(derivedUsers))
				continue;
			
			// calculate/apply riskStatus
			feedbacks.add(caculateRisk(derivedUsers, requestDTO.getSurveyId(),
					(UserDTO) userMapper.map(manager, MappingTypeEnum.MAPTORESPONSE, null)));
		}
		surveyReportDTO.setFeedbacks(feedbacks);
		return surveyReportDTO;
	}
}