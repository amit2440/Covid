package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class FetchLocationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LocationRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocationRequestDTO requestDTO = (LocationRequestDTO) target;
        ValidationUtil.validateFieldRequired(Constant.Field.USER_ID, requestDTO.getUserId(), errors);
    }
}