package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.SubmitFeedbackValidator;

@RequestScope
@Component
public class SubmitFeedbackHandler extends RestControllerHandler {

    @Autowired
    private FeedbackService feedbackService;

    private FeedbackRequestDTO feedbackRequestDTO;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        this.feedbackRequestDTO = (FeedbackRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        Validator validator = new SubmitFeedbackValidator();
        validator.validate(feedbackRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.SUBMIT_FEEDBACK);
    }

    @Override
    protected Object processRequest() throws Exception {
        feedbackService.submitFeedback(feedbackRequestDTO);
        return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.CREATED);
    }
}
