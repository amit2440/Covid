package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.service.QuestionService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.SubmitQuestionValidator;

@RequestScope
@Component
public class SubmitQuestionHandler extends RestControllerHandler {

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
		new SubmitQuestionValidator().validate(requestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		questionService.addQuestion(requestDTO);
		return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.CREATED);
	}
}
