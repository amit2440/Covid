package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackRequestDTO;
import com.med.disease.tracking.app.dto.RiskRequestDTO;
import com.med.disease.tracking.app.service.FeedbackService;
import com.med.disease.tracking.app.service.RiskService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.SubmitFeedbackValidator;
import com.med.disease.tracking.app.validation.SubmitRiskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class SubmitRiskHandler extends RestControllerHandler {

    @Autowired
    private RiskService riskService;

    private RiskRequestDTO riskRequestDTO;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        this.riskRequestDTO = (RiskRequestDTO) request;
        this.bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        Validator validator = new SubmitRiskValidator();
        validator.validate(riskRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.SUBMIT_FEEDBACK);
    }

    @Override
    protected Object processRequest() throws Exception {
        riskService.submitRisk(riskRequestDTO);
        return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.CREATED);
    }
}
