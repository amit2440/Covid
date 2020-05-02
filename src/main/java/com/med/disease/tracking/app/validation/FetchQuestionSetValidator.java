package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class FetchQuestionSetValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionSetRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuestionSetRequestDTO requestDTO = (QuestionSetRequestDTO) target;
		ValidationUtil.validateFieldRequired(Constant.Field.QUESTION_SET_ID, requestDTO.getSetId(), errors);
	}
}
