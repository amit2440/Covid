package com.med.disease.tracking.app.mapper;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.RiskRequestDTO;
import com.med.disease.tracking.app.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SubmitRiskMapper extends Mapper {

    @Override
    protected Object mapToObject(Object objectToMap, Map<String, String> extraField) throws Exception {
        RiskRequestDTO riskRequestDTO = (RiskRequestDTO) objectToMap;
        Risk risk = new Risk();
        User user = new User();
        Survey survey = new Survey();
        user.setUserId(riskRequestDTO.getUserId());
        survey.setSurveyId(riskRequestDTO.getSurveyId());
        risk.setUser(user);
        risk.setSurvey(survey);
        return risk;
    }

    @Override
    protected Object mapToResponse(Object objectToMap, Map<String, String> extraField) throws Exception {
        return null;
    }
}
