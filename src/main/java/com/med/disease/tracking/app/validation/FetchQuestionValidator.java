package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.QuestionRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class FetchQuestionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuestionRequestDTO requestDTO = (QuestionRequestDTO) target;
		ValidationUtil.validateFieldRequired(Constant.Field.QUESTION_ID, requestDTO.getQuestionId(), errors);
	}
}
