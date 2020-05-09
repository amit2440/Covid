package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.util.ValidationUtil;

public class SubmitQuestionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return QuestionRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		QuestionRequestDTO requestDTO = (QuestionRequestDTO) target;
		ValidationUtil.validateBlankField(Constant.Field.QUESTION, requestDTO.getQuestion(), errors);
		ValidationUtil.validateBlankField(Constant.Field.CONTROL, requestDTO.getControl(), errors);
		ValidationUtil.validateFieldRequired(Constant.Field.OPTIONS, requestDTO.getOptions(), errors);
	}
}
