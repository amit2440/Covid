package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.OptionDTO;
import com.med.disease.tracking.app.dto.RiskDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;
import com.med.disease.tracking.app.model.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FetchRiskMapper extends Mapper {

	@Override
	protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
		FetchRiskRequestDTO fetchRiskRequestDTO = (FetchRiskRequestDTO) objectToMap;
		Risk risk = new Risk();
		User user = new User();
		user.setUserId(fetchRiskRequestDTO.getUserId());
		Survey survey = new Survey();
		survey.setSurveyId(fetchRiskRequestDTO.getSurveyId());
		risk.setUser(user);
		risk.setSurvey(survey);
		return risk;
	}

	@Override
	protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
		Risk risk = (Risk) objectToMap;
		RiskDTO riskDTO = new RiskDTO();
		riskDTO.setUserId(risk.getUser().getUserId());
		riskDTO.setSurveyId(risk.getSurvey().getSurveyId());
		riskDTO.setRiskLevel(risk.getRiskLevel());
		return riskDTO;
	}
}
