package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.FetchLocationRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FetchLocationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FetchLocationRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FetchLocationRequestDTO requestDTO = (FetchLocationRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.USER_ID, requestDTO.getUserId(), errors);
    }
}