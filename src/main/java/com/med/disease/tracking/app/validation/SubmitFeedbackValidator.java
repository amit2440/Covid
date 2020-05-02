package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SubmitFeedbackValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FeedbackRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FeedbackRequestDTO feedbackRequestDTO = (FeedbackRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.USER_ID, feedbackRequestDTO.getUserId(), errors);
    }
}
