package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.QuestionSetDTO;
import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;
import com.med.disease.tracking.app.service.QuestionaryService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchQuestionSetValidator;

@RequestScope
@Component
public class FetchQuestionSetHandler extends RestControllerHandler {

	@Autowired
	QuestionaryService questionaryService;
	
	private QuestionSetRequestDTO requestDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		this.requestDTO = (QuestionSetRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		new FetchQuestionSetValidator().validate(requestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		QuestionSetDTO setDTO = questionaryService.getQuestionSet(requestDTO);
		return ObjectUtils.isEmpty(setDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<QuestionSetDTO>(setDTO, HttpStatus.OK);
	}
}
