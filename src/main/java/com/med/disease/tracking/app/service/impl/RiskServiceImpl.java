package com.med.disease.tracking.app.service.impl;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dao.*;
import com.med.disease.tracking.app.dto.*;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.mapper.*;
import com.med.disease.tracking.app.model.*;
import com.med.disease.tracking.app.service.RiskService;
import com.med.disease.tracking.app.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class RiskServiceImpl implements RiskService {

	private static Logger LOGGER = LoggerFactory.getLogger(RiskServiceImpl.class);

	@Autowired
	private SubmitRiskMapper submitRiskMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EPassMapper ePassMapper;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private RiskDAO riskDAO;

	@Autowired
	private OptionDAO optionDAO;

	@Autowired
	UserInfoDAO userDAO;
	
	@Autowired
	private EPassDAO ePassDAO;

	@Autowired
	private FetchRiskMapper fetchRiskMapper;

	@Autowired
	AuditDAO auditDAO;

	@Override
	@Transactional
	public void submitRisk(RiskRequestDTO requestDTO) throws Exception {
		Risk risk = (Risk)submitRiskMapper.map(requestDTO, MappingTypeEnum.MAPTODOMAIN, null);
		List<Integer> allOptionIds = requestDTO.getAnswers().stream().flatMap(answerRequestDTO -> answerRequestDTO.getOptionIds().stream()).collect(Collectors.toList());
		List<String> optionRisks = optionDAO.getOptionRisk(allOptionIds);
		if (optionRisks.stream().anyMatch(riskStatus -> riskStatus.equalsIgnoreCase(Constant.RiskStatus.H)))
			risk.setRiskLevel(Constant.RiskStatus.H);
		else if (optionRisks.stream().anyMatch(riskStatus -> riskStatus.equalsIgnoreCase(Constant.RiskStatus.M)))
			risk.setRiskLevel(Constant.RiskStatus.M);
		else if (optionRisks.stream().anyMatch(riskStatus -> riskStatus.equalsIgnoreCase(Constant.RiskStatus.L)))
			risk.setRiskLevel(Constant.RiskStatus.L);
		else
			risk.setRiskLevel(Constant.RiskStatus.U);
		Optional<Risk> userRisk = riskDAO.getRisk(risk);
		Consumer<Risk> updateRisk = riskStatus ->{
		//	if(!riskStatus.getRiskLevel().equalsIgnoreCase(risk.getRiskLevel())) {
				riskStatus.setCreatedOn(risk.getCreatedOn());
				riskStatus.setRiskLevel(risk.getRiskLevel());
				try {
					if(riskDAO.updateRiskStatus(riskStatus) <= 0) {
						LOGGER.error("Unable to update risk status");
						throw new CovidAppException("Submit risk status failed");
					}
				} catch (DatabaseException exception) {
					throw new CovidAppException(exception.getMessage());
				}
		//	}
		};
		Runnable insertRisk = () -> {
			try {
				if (riskDAO.insertRisk(risk) <= 0) {
					LOGGER.error("Unable to insert risk status");
					throw new CovidAppException("Submit risk status failed");
				}
			} catch (DatabaseException exception) {
				throw new CovidAppException(exception.getMessage());
			}
		};
		userRisk.ifPresentOrElse(updateRisk, insertRisk);

		EPass ePass = new EPass();
		User userForEpass = new User();
		Survey surveyForEpass = new Survey();
		userForEpass.setUserId(requestDTO.getUserId());
		surveyForEpass.setSurveyId(requestDTO.getSurveyId());
		ePass.setUser(userForEpass);
		ePass.setSurvey(surveyForEpass);
		List<EPass> ePassList = ePassDAO.searchUser(ePass);
		if(!ePassList.isEmpty()){
			ePassDAO.deleteEpasses(ePassList.get(0));
			if(!ePassList.get(0).getFromDate().isAfter(LocalDate.now())){
				Audit audit = getAuditObj(ePassList.get(0));
				auditDAO.submitAudit(audit);
			}
		}

	}

	private Audit getAuditObj(EPass ePass) {
		User user = new User();
		user.setUserId(ePass.getUser().getUserId());

		Survey survey = new Survey();
		survey.setSurveyId(ePass.getSurvey().getSurveyId());

		User createdBy = new User();
		createdBy.setUserId(ePass.getUser().getUserId());

		Audit audit = new Audit();
		audit.setUser(user);
		audit.setSurvey(survey);
		audit.setIsAllowed(true);
		audit.setToDate(ePass.getToDate().isBefore(LocalDate.now()) ? ePass.getToDate() : LocalDate.now());
		audit.setCreatedBy(createdBy);
		audit.setFromDate(ePass.getFromDate());
		return audit;
	}

	@Override
	@Transactional
	public RiskDTO fetchRisk(FetchRiskRequestDTO fetchRiskRequestDTO) throws Exception {
		Risk risk = (Risk) fetchRiskMapper.map(fetchRiskRequestDTO, MappingTypeEnum.MAPTODOMAIN,
				null);
		Optional<Risk> userRisk = riskDAO.getRisk(risk);
		return userRisk.isEmpty() ? null :
				(RiskDTO) fetchRiskMapper.map(userRisk.get(), MappingTypeEnum.MAPTORESPONSE, null);
	}

	@Override
	@Transactional
	public SurveyFeedbackDTO fetchSurveyFeedbacks(FetchRiskRequestDTO requestDTO) throws Exception {
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

	public List<User> getUsers(List<User> users) throws Exception{
		List<User> allUsers = new ArrayList<>();
		for(User user : users){
			List<User> inputUser = userDAO.searchUser(user);
			allUsers.addAll(inputUser);
		};
		if (ObjectUtils.isEmpty(allUsers)) {
			LOGGER.error("Users Not Found");
			return null;
		}
		return allUsers;
	}

	public SurveyFeedbackDTO caculateRisk(List<User> derivedUsers, Integer surveyId, UserDTO manager) throws Exception {
		SurveyFeedbackDTO surveyFeedback = new SurveyFeedbackDTO();
		surveyFeedback.setManager(manager);
		List<Risk> derivedUsersForRiskStatus = derivedUsers.stream().map(user ->{
			Risk userRisk = new Risk();
			Survey survey = new Survey();
			survey.setSurveyId(surveyId);
			userRisk.setSurvey(survey);
			userRisk.setUser(user);
			return userRisk;
		}).collect(Collectors.toList());

		List<Risk> derivedUsersRisks = riskDAO.getRisk(derivedUsersForRiskStatus);

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
		userDTOs.forEach(usr -> {
			Optional<Risk> userRisk = derivedUsersRisks.stream().filter(u -> usr.getUserId().equals(u.getUser().getUserId())).findAny();
			userRisk.ifPresentOrElse((risk) -> {
				if (!ObjectUtils.isEmpty(usr.getEpass().getToDate()) && usr.getEpass().getToDate().isBefore(LocalDate.now())) {
					usr.setRiskStatus(Constant.RiskStatus.U);
					usr.getEpass().setIsAllowed(false);
					usr.getEpass().setToDate(null);
					usr.getEpass().setFromDate(null);
				}
				else {
					usr.setRiskStatus(risk.getRiskLevel());
				}
				if(risk.getCreatedOn()!=null)
					usr.setSurveySubmittedOn(risk.getCreatedOn().toLocalDate());
			}, () -> usr.setRiskStatus(Constant.RiskStatus.U));
		});
		surveyFeedback.setAllowedEpassCount(userDTOs.stream().filter(usr -> usr.getEpass().getIsAllowed()
																				&& !usr.getEpass().getToDate().isBefore(LocalDate.now())
																					&& !usr.getEpass().getFromDate().isAfter(LocalDate.now())).count());
		surveyFeedback.setUsers(userDTOs);
		return surveyFeedback;
	}

	@Override
	public SurveyReportDTO fetchAllSurveyFeedbacks(FetchRiskRequestDTO requestDTO) throws Exception {
		// fetch all managers
		User searchMgrs = new User();
		searchMgrs.setRole(Constant.Role.MANAGER);
		User searchAdmins = new User();
		searchAdmins.setRole(Constant.Role.ADMIN);
		List<User> managersAndAdmins = getUsers(Arrays.asList(searchAdmins,searchMgrs));

		if(ObjectUtils.isEmpty(managersAndAdmins))
			return null;
		
		SurveyReportDTO surveyReportDTO = new SurveyReportDTO();
		List<SurveyFeedbackDTO> feedbacks = new ArrayList<>();
		
		for(User user : managersAndAdmins) {
			// get users under manager
			User userToSearch = new User();
			userToSearch.setMgrID(user.getUserId());
			List<User> derivedUsers = userDAO.searchUser(userToSearch);
			
			if(CollectionUtils.isEmpty(derivedUsers))
				continue;
			
			// calculate/apply riskStatus
			feedbacks.add(caculateRisk(derivedUsers, requestDTO.getSurveyId(),
					(UserDTO) userMapper.map(user, MappingTypeEnum.MAPTORESPONSE, null)));
		}
		surveyReportDTO.setFeedbacks(feedbacks);
		return surveyReportDTO;
	}
}