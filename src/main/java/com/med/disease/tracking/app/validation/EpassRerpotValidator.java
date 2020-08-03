package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class EpassRerpotValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return EPassRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EPassRequestDTO requestDTO = (EPassRequestDTO) target;
//		ValidationUtil.validateToDate(Constant.Field.TO_DATE, requestDTO.getToDate(), errors);
	}
}
