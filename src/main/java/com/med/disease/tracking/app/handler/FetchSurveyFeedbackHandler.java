package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchAdminFeedbackValidator;

@RequestScope
@Component
public class FetchSurveyFeedbackHandler extends RestControllerHandler {

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
		new FetchAdminFeedbackValidator().validate(fetchFeedbackRequestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.FEEDBACK_FETCH_MANAGER);
	}

	@Override
	protected Object processRequest() throws Exception {
		SurveyReportDTO surveyReportDTO = feedbackService.fetchAllSurveyFeedbacks(fetchFeedbackRequestDTO);
		return ObjectUtils.isEmpty(surveyReportDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<SurveyReportDTO>(surveyReportDTO, HttpStatus.OK);
	}
}
