package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.SurveyReportDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.service.RiskService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchAdminFeedbackValidator;
import com.med.disease.tracking.app.validation.FetchAdminRiskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class FetchSurveyRiskHandler extends RestControllerHandler {

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
		new FetchAdminRiskValidator().validate(fetchRiskRequestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.RISK_FETCH_MANAGER);
	}

	@Override
	protected Object processRequest() throws Exception {
		SurveyReportDTO surveyReportDTO = riskService.fetchAllSurveyFeedbacks(fetchRiskRequestDTO);
		return ObjectUtils.isEmpty(surveyReportDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<SurveyReportDTO>(surveyReportDTO, HttpStatus.OK);
	}
}
