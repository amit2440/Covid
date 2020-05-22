package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.constant.Constant.Authority;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.handler.FetchEPassHandler;
import com.med.disease.tracking.app.handler.SubmitEPassHandler;

@RestController
public class EPassController {

	@Autowired
	BeanFactory beanFactory;

	@PreAuthorize(Authority.MANAGER_OR_ADMIN)
	@PostMapping(value = "/surveys/{surveyId}/epasses", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitepass(@RequestBody EPassRequestDTO requestDTO, 
			@PathVariable(required = true, name = "surveyId") String surveyId,
			BindingResult result)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(SubmitEPassHandler.class).handle(requestDTO, result, surveyId);
	}
	
	// @PreAuthorize(Authority.USER)
	@GetMapping(value = "/surveys/{surveyId}/epasses/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getepass(@ModelAttribute EPassRequestDTO requestDTO, BindingResult result)
			throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchEPassHandler.class).handle(requestDTO, result);
	}
}
