package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackForSurveyDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;

@RequestScope
@Component
public class FetchSurveyFeedbackHandler extends RestControllerHandler{

    private  FetchFeedbackRequestDTO fetchFeedbackRequestDTO;

    @Autowired
    private FeedbackService feedbackService;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) request;
    }

    @Override
    protected void validateRequest() {}

    @Override
    protected Object processRequest() throws Exception {
        FeedbackForSurveyDTO feedbackForSurveyDTO = feedbackService.fetchFeedbacksForSurvey(fetchFeedbackRequestDTO);
        return ObjectUtils.isEmpty(feedbackForSurveyDTO) ?
                new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NOT_FOUND):
                new ResponseEntity<FeedbackForSurveyDTO>(feedbackForSurveyDTO, HttpStatus.OK);
    }
}
