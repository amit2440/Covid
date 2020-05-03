package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class UpdateQuestionSetValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return SurveyRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SurveyRequestDTO requestDTO = (SurveyRequestDTO) target;
		ValidationUtil.validateFieldRequired(Constant.Field.QUESTION_SET_ID, requestDTO.getSurveyId(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.ISACTIVE, requestDTO.getIsActive(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.DESCRIPTION, requestDTO.getDescription(), errors);
	}
}
