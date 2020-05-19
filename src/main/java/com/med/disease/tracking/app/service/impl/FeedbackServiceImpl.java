package com.med.disease.tracking.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dao.UserInfoDAO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.mapper.FetchFeedbackMapper;
import com.med.disease.tracking.app.mapper.MappingTypeEnum;
import com.med.disease.tracking.app.mapper.SubmitFeedbackMapper;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.model.UserRisk;
import com.med.disease.tracking.app.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	private static Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private SubmitFeedbackMapper submitFeedbackMapper;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	UserInfoDAO userDAO;

	@Autowired
	private FetchFeedbackMapper fetchFeedbackMapper;

	@Override
	@Transactional
	public void submitFeedback(FeedbackRequestDTO feedbackRequestDTO) throws Exception {
		Object object = submitFeedbackMapper.map(feedbackRequestDTO, MappingTypeEnum.MAPTODOMAIN, null);
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
				: caculateRisk(derivedUsers, requestDTO.getSurveyId(), toDTO(manager));
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
		userRiskIn.setUserId(manager.getUserId());
		userRiskIn.setSurveyId(surveyId);
		
		List<UserRisk> riskStates = feedbackDAO.getUserRisks(userRiskIn);
		
		List<UserDTO> userDTOs = derivedUsers.stream().map(usr -> toDTO(usr))
				.collect(Collectors.toList());

		userDTOs.stream().forEach(usr -> {
			usr.setRiskStatus(getRiskStatus(riskStates, usr.getUserId()));
		});
		surveyFeedback.setUsers(userDTOs);
		return surveyFeedback;
	}

	private String getRiskStatus(List<UserRisk> riskStates, Integer userId) {
		for (String riskStatus : Arrays.asList(Constant.RiskStatus.H, Constant.RiskStatus.M, Constant.RiskStatus.L)) {
			Optional<UserRisk> riskOp = riskStates.stream()
					.filter(state -> userId.equals(state.getUserId()) && riskStatus.equals(state.getRiskStatus()))
					.findFirst();
			if (riskOp.isPresent()) {
				return riskStatus;
			}
		}
		return Constant.RiskStatus.U;
	}

	private UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.setUserId(user.getUserId());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setRole(user.getRole());
			userDTO.setWorkLocation(user.getWorkLocation());
		}
		return userDTO;
	}

	@Override
	public SurveyReportDTO fetchAllSurveyFeedbacks(FetchFeedbackRequestDTO requestDTO) throws Exception {
		User reqUser = new User();
		reqUser.setUserId(requestDTO.getUserId());
		
		// get admin info
		User admin = getUserInfo(reqUser);
		
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
			feedbacks.add(caculateRisk(derivedUsers, requestDTO.getSurveyId(), toDTO(manager)));
		}
		surveyReportDTO.setAdmin(toDTO(admin));
		surveyReportDTO.setFeedbacks(feedbacks);
		return surveyReportDTO;
	}
}