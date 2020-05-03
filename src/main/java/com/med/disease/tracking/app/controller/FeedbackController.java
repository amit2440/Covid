package com.med.disease.tracking.app.controller;

import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.dto.request.UserRequestDTO;
import com.med.disease.tracking.app.handler.FetchFeedbackHandler;
import com.med.disease.tracking.app.handler.SubmitFeedbackHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @Autowired
    BeanFactory beanFactory;

    @PostMapping(value = "/user/feedbacks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO,
                                         BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(SubmitFeedbackHandler.class)
                .handle(feedbackRequestDTO, bindingResult);
    }

    @GetMapping(value = "/admin/feedbacks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchFeedback(@ModelAttribute UserRequestDTO userRequestDTO,
                                           BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchFeedbackHandler.class)
                .handle(userRequestDTO, bindingResult);
    }
}
