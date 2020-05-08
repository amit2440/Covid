package com.med.disease.tracking.app.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.handler.FetchFeedbackHandler;
import com.med.disease.tracking.app.handler.FetchSurveyFeedbackHandler;
import com.med.disease.tracking.app.handler.SubmitFeedbackHandler;

@RestController
public class FeedbackController {

    @Autowired
    BeanFactory beanFactory;

    @PostMapping(value = "/feedbacks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO,
                                         BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(SubmitFeedbackHandler.class)
                .handle(feedbackRequestDTO, bindingResult);
    }

    @GetMapping(value = "/surveys/{surveyId}/feedbacks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchFeedback(@ModelAttribute FetchFeedbackRequestDTO fetchFeedbackRequestDTO,
                                           BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchFeedbackHandler.class)
                .handle(fetchFeedbackRequestDTO, bindingResult);
    }

    @GetMapping(value = "/surveys/{surveyId}/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAllFeedback(@ModelAttribute FetchFeedbackRequestDTO fetchFeedbackRequestDTO,
                                              BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchSurveyFeedbackHandler.class)
                .handle(fetchFeedbackRequestDTO, bindingResult);
    }
}
