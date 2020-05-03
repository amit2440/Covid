package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.UserRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FetchFeedbackValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDTO userRequestDTO = (UserRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.USER_ID,userRequestDTO.getUserId(), errors);
    }
}
