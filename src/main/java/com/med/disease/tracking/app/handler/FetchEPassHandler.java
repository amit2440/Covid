package com.med.disease.tracking.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.service.EPassService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.FetchEPassValidator;

@RequestScope
@Component
public class FetchEPassHandler extends RestControllerHandler {

	@Autowired
	private EPassService ePassService;

	private EPassRequestDTO requestDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		this.requestDTO = (EPassRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		new FetchEPassValidator().validate(requestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.SUBMIT_EPASS);
	}

	@Override
	protected Object processRequest() throws Exception {
		EPassDTO  ePassDTO = ePassService.fetchEPass(requestDTO);
		return ObjectUtils.isEmpty(ePassDTO)
				? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.NO_CONTENT)
				: new ResponseEntity<EPassDTO>(ePassDTO, HttpStatus.OK);
	}
}
