package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.service.QuestionService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchQuestionValidator;

@RequestScope
@Component
public class FetchQuestionHandler extends RestControllerHandler {

	@Autowired
	QuestionService questionService;
	
	private QuestionRequestDTO requestDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		this.requestDTO = (QuestionRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		Validator validator = new FetchQuestionValidator();
		validator.validate(requestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		QuestionDTO questionDTO = questionService.getQuestion(requestDTO);
		return ObjectUtils.isEmpty(questionDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<QuestionDTO>(questionDTO, HttpStatus.OK);
	}
}
