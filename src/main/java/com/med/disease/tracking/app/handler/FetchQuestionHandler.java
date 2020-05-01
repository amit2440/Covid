package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.dto.QuestionRequestDTO;
import com.med.disease.tracking.app.service.QuestionaryService;

@RequestScope
@Component
public class FetchQuestionHandler extends RestControllerHandler {

	@Autowired
	QuestionaryService questionaryService;
	
	private QuestionRequestDTO requestDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		this.requestDTO = (QuestionRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object processRequest() throws Exception {
		QuestionDTO questionDTO = questionaryService.getQuestion(requestDTO);
		return ObjectUtils.isEmpty(questionDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<QuestionDTO>(questionDTO, HttpStatus.OK);
	}
}
