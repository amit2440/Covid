package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.RiskRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SubmitRiskValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RiskRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RiskRequestDTO riskRequestDTO = (RiskRequestDTO) target;
        ValidationUtil.validateBlankField(Constant.Field.USER_ID, riskRequestDTO.getUserId(), errors);
        ValidationUtil.validateBlankField(Constant.Field.SURVEY_ID, riskRequestDTO.getSurveyId(), errors);
        ValidationUtil.validateAnswers(Constant.Field.ANSWERS, riskRequestDTO.getAnswers(), errors);
    }
}
