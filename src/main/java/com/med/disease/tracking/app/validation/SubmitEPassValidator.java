package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class SubmitEPassValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EPassRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EPassRequestDTO requestDTO = (EPassRequestDTO) target;
		ValidationUtil.validateBlankField(Constant.Field.USER_ID, requestDTO.getUserId(), errors);
		ValidationUtil.validateBlankField(Constant.Field.SURVEY_ID, requestDTO.getSurveyId(), errors);
		ValidationUtil.validateToDate(Constant.Field.TO_DATE, requestDTO.getToDate(), errors);
		ValidationUtil.validateToDate(Constant.Field.FROM_DATE, requestDTO.getFromDate(), errors);
		ValidationUtil.isUserEPassAllowed(Constant.Field.USER_ID, requestDTO, errors);
	}
}
