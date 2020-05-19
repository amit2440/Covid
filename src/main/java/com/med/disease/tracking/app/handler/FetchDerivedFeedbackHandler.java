package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchFeedbackValidator;

@RequestScope
@Component
public class FetchDerivedFeedbackHandler extends RestControllerHandler {

	private FetchFeedbackRequestDTO fetchFeedbackRequestDTO;

	@Autowired
	private FeedbackService feedbackService;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		bindingResult = result;
		fetchFeedbackRequestDTO = (FetchFeedbackRequestDTO) request;
	}

	@Override
	protected void validateRequest() {
		Validator fetchFeedbackValidator = new FetchFeedbackValidator();
		fetchFeedbackValidator.validate(fetchFeedbackRequestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.FEEDBACK_FETCH_MANAGER);
	}

	@Override
	protected Object processRequest() throws Exception {
		SurveyFeedbackDTO surveyFeedbackDTO = feedbackService.fetchSurveyFeedbacks(fetchFeedbackRequestDTO);
		return ObjectUtils.isEmpty(surveyFeedbackDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NOT_FOUND)
				: new ResponseEntity<SurveyFeedbackDTO>(surveyFeedbackDTO, HttpStatus.OK);
	}
}
