package com.med.disease.tracking.app.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EPassDTO;
import com.med.disease.tracking.app.dto.EPassRequestDTO;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.ReportRequestDTO;
import com.med.disease.tracking.app.dto.ReportResponseDTO;
import com.med.disease.tracking.app.service.ReportService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.EpassRerpotValidator;


@RequestScope
@Component
public class EpassReportHandler  extends RestControllerHandler {


	@Autowired
	ReportService reportService;

	private EPassRequestDTO reportRequestDTO;

	private String operation;
	
	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		// TODO Auto-generated method stub
		this.reportRequestDTO = (EPassRequestDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub
		Validator validator = new EpassRerpotValidator();
		validator.validate(reportRequestDTO, bindingResult);
//		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		// TODO Auto-generated method stub
		ReportResponseDTO reportResponseDTO  =  new ReportResponseDTO();
		List<EPassDTO> epassDTOList = (List<EPassDTO>) reportService.allowedEpassReport(reportRequestDTO);
		Integer count =(Integer) reportService.countAllowedEpass(reportRequestDTO);
		reportResponseDTO.setEpassDTOList(epassDTOList);
		reportResponseDTO.setCount(count);
		
		return  ObjectUtils.isEmpty(epassDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity(reportResponseDTO, HttpStatus.OK);
	}

}
