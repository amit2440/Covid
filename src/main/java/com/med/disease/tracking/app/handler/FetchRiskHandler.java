package com.med.disease.tracking.app.handler;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.FeedbackDTO;
import com.med.disease.tracking.app.dto.RiskDTO;
import com.med.disease.tracking.app.dto.request.FetchFeedbackRequestDTO;
import com.med.disease.tracking.app.dto.request.FetchRiskRequestDTO;
import com.med.disease.tracking.app.model.Risk;
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
public class FetchRiskHandler extends RestControllerHandler{

    @Autowired
    private RiskService riskService;

    private FetchRiskRequestDTO fetchRiskRequestDTO;

    @Override
    protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
        fetchRiskRequestDTO = (FetchRiskRequestDTO) request;
        bindingResult = result;
    }

    @Override
    protected void validateRequest() {
        Validator fetchRiskValidator = new FetchRiskValidator();
        fetchRiskValidator.validate(fetchRiskRequestDTO, bindingResult);
        ErrorUtil.processError(bindingResult, Constant.Module.FEEDBACK_FETCH);
    }

    @Override
    protected Object processRequest() throws Exception {
        RiskDTO riskDTO = riskService.fetchRisk(fetchRiskRequestDTO);
        return ObjectUtils.isEmpty(riskDTO) ?
                new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NO_CONTENT):
                new ResponseEntity<RiskDTO>(riskDTO, HttpStatus.OK);
    }
}
