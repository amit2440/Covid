package com.med.disease.tracking.app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.constant.ReportConstant;
import com.med.disease.tracking.app.dto.ReportRequestDTO;
import com.med.disease.tracking.app.service.ReportService;
import com.med.disease.tracking.app.service.impl.RegisterEmployeeServiceImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.UserInfoValidator;

@RequestScope
@Component
public class ReportHandler extends RestControllerHandler {

	private static final Logger logger = LoggerFactory.getLogger(RegisterEmployeeServiceImpl.class);

	@Autowired
	ReportService reportService;

	private ReportRequestDTO reportRequestDTO;

	private String operation;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.reportRequestDTO = (ReportRequestDTO) request;
		this.operation = pathParam[0];
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub
		Validator validator = new UserInfoValidator();
		validator.validate(reportRequestDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		// TODO Auto-generated method stub
		if ("".equals(reportRequestDTO.getReportName())
				&& reportRequestDTO.getReportName().equalsIgnoreCase(ReportConstant.RPT_FEEDBACK)) {
			Object userDTOList = reportService.getFeedbackReportForManager(reportRequestDTO);
//			return  ObjectUtils.isEmpty(userDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
//						: new ResponseEntity<List>(userDTOList, HttpStatus.OK);
		} else {

		}
		return null;
	}

}
