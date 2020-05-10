package com.med.disease.tracking.app.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.SurveyDTO;
import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.service.SurveyService;

@RequestScope
@Component
public class FetchAllSurveyHandler extends RestControllerHandler {

	@Autowired
	SurveyService surveyService;
	
	private SurveyRequestDTO requestDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		this.requestDTO = (SurveyRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {}

	@Override
	protected Object processRequest() throws Exception {
		List<SurveyDTO> setDTO = surveyService.getSurveys(requestDTO);
		return ObjectUtils.isEmpty(setDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<List<SurveyDTO>>(setDTO, HttpStatus.OK);
	}
}
