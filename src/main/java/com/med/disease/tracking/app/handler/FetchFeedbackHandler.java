package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.QuestionDTO;
import com.med.disease.tracking.app.model.Feedback;
import com.med.disease.tracking.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

public class FetchFeedbackHandler implements RestControllerHandler {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    FeedbackRequestDTO feedbackRequestDTO;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        this.feedbackRequestDTO = (FeedbackRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        //TODO
    }

    @Override
    protected Object processRequest() throws Exception {
        feedbackService.submitFeedback(feedbackRequestDTO);
        return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK);
    }
}
