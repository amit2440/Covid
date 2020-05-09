package com.med.disease.tracking.app.validation;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.LocationRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddLocationValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LocationRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LocationRequestDTO requestDTO = (LocationRequestDTO) target;
		ValidationUtil.validateFieldRequired(Constant.Field.USER_ID, requestDTO.getUserId(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.AREA, requestDTO.getArea(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.LONGITUDE, requestDTO.getLongitude(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.LATITUDE, requestDTO.getLatitude(), errors);
	}
}
