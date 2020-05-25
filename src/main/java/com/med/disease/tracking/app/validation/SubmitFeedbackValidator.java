package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class SubmitFeedbackValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FeedbackRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) target;
        ValidationUtil.validateBlankField(Constant.Field.USER_ID, feedbackRequestDTO.getUserId(), errors);
        ValidationUtil.validateBlankField(Constant.Field.SURVEY_ID, feedbackRequestDTO.getSurveyId(), errors);
        ValidationUtil.validateAnswers(Constant.Field.ANSWERS, feedbackRequestDTO.getAnswers(), errors);
    }
}
