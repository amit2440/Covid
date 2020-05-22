package com.med.disease.tracking.app.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dao.EPassDAO;
import com.med.disease.tracking.app.dao.FeedbackDAO;
import com.med.disease.tracking.app.dao.UserInfoDAO;
import com.med.disease.tracking.app.model.EPass;
import com.med.disease.tracking.app.model.Survey;
import com.med.disease.tracking.app.model.User;
import com.med.disease.tracking.app.model.UserRisk;

@Component
public class CommonUtil {

	@Autowired
	UserInfoDAO userDAO;
	
	@Autowired
	EPassDAO ePassDAO;

	@Autowired
	FeedbackDAO feedbackDAO;

	public boolean isEPassApplicable(Integer userId, Integer surveyId) throws Exception {
		return Arrays.asList(Constant.RiskStatus.L, Constant.RiskStatus.M)
				.contains(getUserRiskStatus(userId, surveyId));
	}

	public String getUserRiskStatus(Integer userId, Integer surveyId) throws Exception {
		UserRisk userRiskIn = new UserRisk();
		userRiskIn.setUserId(userId);
		userRiskIn.setSurveyId(surveyId);

		List<UserRisk> riskStates = feedbackDAO.getUserRisks(userRiskIn);
		return getRiskStatus(riskStates, userId);
	}

	public static String getRiskStatus(List<UserRisk> riskStates, Integer userId) {
		if(ObjectUtils.isEmpty(riskStates))
			return Constant.RiskStatus.U;
		
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
	
	public boolean isCurrentlyEpassAllowed(Integer userId, Integer surveyId) throws Exception {
		User userToSearch = new User();
		userToSearch.setUserId(userId);

		Survey surveyToSearch = new Survey();
		surveyToSearch.setSurveyId(surveyId);

		EPass ePassToSearch = new EPass();
		ePassToSearch.setUser(userToSearch);
		ePassToSearch.setSurvey(surveyToSearch);

		List<EPass> ePassUser = ePassDAO.searchUser(ePassToSearch);

		return !CollectionUtils.isEmpty(ePassUser) && true == ePassUser.get(0).getIsAllowed();
	}
	
}
