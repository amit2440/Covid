package com.med.disease.tracking.app.controller;

import com.med.disease.tracking.app.dto.RiskRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.handler.FetchDerivedRiskHandler;
import com.med.disease.tracking.app.handler.FetchSurveyRiskHandler;
import com.med.disease.tracking.app.handler.SubmitRiskHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class RiskController {

    @Autowired
    BeanFactory beanFactory;

    @PostMapping(value = "/risk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> submitRisk(@RequestBody RiskRequestDTO riskRequestDTO,
                                         BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(SubmitRiskHandler.class)
                .handle(riskRequestDTO, bindingResult);
    }

    /*@GetMapping(value = "/surveys/{surveyId}/feedbacks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchFeedback(@ModelAttribute FetchFeedbackRequestDTO fetchFeedbackRequestDTO,
                                           BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchFeedbackHandler.class)
                .handle(fetchFeedbackRequestDTO, bindingResult);
    }*/

    @GetMapping(value = "/reports/surveys/{surveyId}/risks/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchDerivedRisk(@ModelAttribute FetchRiskRequestDTO fetchRiskRequestDTO,
    		BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchDerivedRiskHandler.class)
                .handle(fetchRiskRequestDTO, bindingResult);
    }
    
    @GetMapping(value = "/reports/surveys/{surveyId}/risks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAllRisks(@ModelAttribute FetchRiskRequestDTO fetchRiskRequestDTO,
    		BindingResult bindingResult) throws Exception{
        return (ResponseEntity<?>) beanFactory.getBean(FetchSurveyRiskHandler.class)
                .handle(fetchRiskRequestDTO, bindingResult);
    }
}
