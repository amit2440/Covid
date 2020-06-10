package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FetchAdminRiskValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FetchRiskRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FetchRiskRequestDTO fetchRiskRequestDTO = (FetchRiskRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.SURVEY_ID, fetchRiskRequestDTO.getSurveyId(), errors);
    }
}
