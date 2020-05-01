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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class FetchFeedbackHandler extends RestControllerHandler {

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
        //TODO
    }

    @Override
    protected Object processRequest() throws Exception {
        feedbackService.submitFeedback(feedbackRequestDTO);
        return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK);
    }
}
