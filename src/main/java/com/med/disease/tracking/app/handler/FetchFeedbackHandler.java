package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.request.UserRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchFeedbackValidator;
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
public class FetchFeedbackHandler extends RestControllerHandler{

    @Autowired
    private FeedbackService feedbackService;

    private UserRequestDTO userRequestDTO;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        userRequestDTO = (UserRequestDTO) request;
        bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        Validator fetchFeedbackValidator = new FetchFeedbackValidator();
        fetchFeedbackValidator.validate(userRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.FEEDBACK_FETCH);
    }

    @Override
    protected Object processRequest() throws Exception {
        FeedbackDTO feedbackDTO = feedbackService.fetchFeedbacks(userRequestDTO);
        return ObjectUtils.isEmpty(feedbackDTO) ?
                new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.BAD_GATEWAY):
                new ResponseEntity<FeedbackDTO>(feedbackDTO, HttpStatus.OK);
    }
}
