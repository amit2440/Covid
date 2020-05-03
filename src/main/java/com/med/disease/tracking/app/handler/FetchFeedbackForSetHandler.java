package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackForSetDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchFeedbackForSetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class FetchFeedbackForSetHandler extends RestControllerHandler{

    private  FetchFeedbackRequestDTO fetchFeedbackRequestDTO;

    @Autowired
    private FeedbackService feedbackService;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) request;
        this.bindingResult = bindingResult;
    }

    @Override
    protected void validateRequest() {
        Validator validator = new FetchFeedbackForSetValidator();
        validator.validate(fetchFeedbackRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.FEEDBACK_FETCH);
    }

    @Override
    protected Object processRequest() throws Exception {
        FeedbackForSetDTO feedbackForSetDTO = feedbackService.fetchFeedbacksForSurvey(fetchFeedbackRequestDTO);
        return ObjectUtils.isEmpty(feedbackForSetDTO) ?
                new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NOT_FOUND):
                new ResponseEntity<FeedbackForSetDTO>(feedbackForSetDTO, HttpStatus.OK);
    }
}