package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class FetchFeedbackForSetValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FetchFeedbackRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtil.validateFieldRequired(Constant.Field.SURVEY_ID, ((FetchFeedbackRequestDTO) target).getSurveyId(), errors);
    }
}
