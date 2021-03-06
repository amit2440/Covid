package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.request.SurveyRequestDTO;
import com.med.disease.tracking.app.handler.FetchAllSurveyHandler;
import com.med.disease.tracking.app.handler.FetchSurveyHandler;
import com.med.disease.tracking.app.handler.FetchSurveyQuestionHandler;
import com.med.disease.tracking.app.handler.SubmitSurveyHandler;
import com.med.disease.tracking.app.handler.SubmitSurveyQuestionHandler;
import com.med.disease.tracking.app.handler.UpdateSurveyHandler;

@RestController
public class SurveyController {

	@Autowired
	BeanFactory beanFactory;

	@GetMapping(path = "/surveys/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchSurvey(@ModelAttribute SurveyRequestDTO requestDTO, BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchSurveyHandler.class).handle(requestDTO, bindingResult);
	}
	
	@GetMapping(path = "/surveys", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchSurveys(@ModelAttribute SurveyRequestDTO requestDTO, BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchAllSurveyHandler.class).handle(requestDTO, bindingResult);
	}

	@PostMapping(path = "/surveys", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitSurvey(@RequestBody SurveyRequestDTO requestDTO, BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(SubmitSurveyHandler.class).handle(requestDTO, bindingResult);
	}

	@PutMapping(path = "/surveys/{surveyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSurvey(@RequestBody SurveyRequestDTO requestDTO,
			@PathVariable(required = true, name = "surveyId") String surveyId, BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UpdateSurveyHandler.class).handle(requestDTO, bindingResult,
				surveyId);
	}

	@PostMapping(path = "/surveys/{surveyId}/questions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitSurveyQuestions(@RequestBody SurveyRequestDTO requestDTO, 
			@PathVariable(required = true, name = "surveyId") String surveyId, 
			BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(SubmitSurveyQuestionHandler.class).handle(requestDTO, bindingResult, surveyId);
	}
	
	@GetMapping(path = "/surveys/{surveyId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSurveyQuestions(@ModelAttribute SurveyRequestDTO requestDTO, 
			@PathVariable(required = true, name = "surveyId") String surveyId, 
			BindingResult bindingResult)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchSurveyQuestionHandler.class).handle(requestDTO, bindingResult, surveyId);
	}
}
