package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.SurveyFeedbackDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.service.RiskService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchFeedbackValidator;
import com.med.disease.tracking.app.validation.FetchRiskValidator;
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
public class FetchDerivedRiskHandler extends RestControllerHandler {

	private FetchRiskRequestDTO fetchRiskRequestDTO;

	@Autowired
	private RiskService riskService;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		bindingResult = result;
		fetchRiskRequestDTO = (FetchRiskRequestDTO) request;
	}

	@Override
	protected void validateRequest() {
		Validator fetchRiskValidator = new FetchRiskValidator();
		fetchRiskValidator.validate(fetchRiskRequestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.RISK_FETCH_MANAGER);
	}

	@Override
	protected Object processRequest() throws Exception {
		SurveyFeedbackDTO surveyFeedbackDTO = riskService.fetchSurveyFeedbacks(fetchRiskRequestDTO);
		return ObjectUtils.isEmpty(surveyFeedbackDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<SurveyFeedbackDTO>(surveyFeedbackDTO, HttpStatus.OK);
	}
}
