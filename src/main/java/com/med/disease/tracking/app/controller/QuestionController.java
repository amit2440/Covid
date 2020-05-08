package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.handler.FetchQuestionHandler;

@RestController
public class QuestionController {

	@Autowired
	BeanFactory beanFactory;

	@GetMapping(path = "/questions/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchQuestions(@ModelAttribute QuestionRequestDTO requestDTO, 
			BindingResult bindingResult) throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchQuestionHandler.class)
				.handle(requestDTO, bindingResult);
	}
}
