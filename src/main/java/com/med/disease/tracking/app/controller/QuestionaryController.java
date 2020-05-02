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

import com.med.disease.tracking.app.dto.request.QuestionRequestDTO;
import com.med.disease.tracking.app.dto.request.QuestionSetRequestDTO;
import com.med.disease.tracking.app.handler.FetchQuestionHandler;
import com.med.disease.tracking.app.handler.FetchQuestionSetHandler;
import com.med.disease.tracking.app.handler.SubmitQuestionSetHandler;
import com.med.disease.tracking.app.handler.UpdateQuestionSetHandler;

@RestController
public class QuestionaryController {

	@Autowired
	BeanFactory beanFactory;

	@GetMapping(path = "/admin/questions/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchQuestions(@ModelAttribute QuestionRequestDTO requestDTO, 
			BindingResult bindingResult) throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchQuestionHandler.class)
				.handle(requestDTO, bindingResult);
	}
	
	@GetMapping(path = "/admin/questionsets/{setId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchQuestionSet(@ModelAttribute QuestionSetRequestDTO requestDTO, 
			BindingResult bindingResult) throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(FetchQuestionSetHandler.class)
				.handle(requestDTO, bindingResult);
	}
	
	@PostMapping(path = "/admin/questionsets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> submitQuestionSet(@RequestBody QuestionSetRequestDTO requestDTO, 
			BindingResult bindingResult) throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(SubmitQuestionSetHandler.class)
				.handle(requestDTO, bindingResult);
	}
	
	@PutMapping(path = "/admin/questionsets/{setId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateQuestionSet(@RequestBody QuestionSetRequestDTO requestDTO, 
			@PathVariable(required = true, name = "setId") String setId , 
			BindingResult bindingResult) throws Exception {
		return (ResponseEntity<?>) beanFactory.getBean(UpdateQuestionSetHandler.class)
				.handle(requestDTO, bindingResult, setId);
	}
}
