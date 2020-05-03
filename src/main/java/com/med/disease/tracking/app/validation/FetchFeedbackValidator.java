package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FetchFeedbackValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FetchFeedbackRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FetchFeedbackRequestDTO fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.USER_ID, fetchFeedbackRequestDTO.getUserId(), errors);
        ValidationUtil.validateFieldRequired(Constant.Field.QUESTION_SET_ID, fetchFeedbackRequestDTO.getSurveyId(), errors);
    }
}
