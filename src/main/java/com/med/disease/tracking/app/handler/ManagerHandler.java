package com.med.disease.tracking.app.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.med.disease.tracking.app.dto.ManagerDTO;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.exception.ErrorResponse;
import com.med.disease.tracking.app.service.ManagerService;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.ManagerValidator;

@RequestScope
@Component
public class ManagerHandler extends RestControllerHandler {

	private static final Logger logger = LoggerFactory.getLogger(ManagerHandler.class);

	private ManagerDTO managerDTO;
	
	@Autowired
	ManagerService managerService;
	
	
	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		// TODO Auto-generated method stub
		this.managerDTO = (ManagerDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub
		Validator validator = new ManagerValidator();
		validator.validate(managerDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.REG_MANAGER);
	}

	@Override
	protected Object processRequest() throws Exception {
		// TODO Auto-generated method stub
		int res = managerService.insertManager(managerDTO);
		if(res > 0) {
			return new ResponseEntity<String>("User Information Update Successfully", HttpStatus.OK);
		}else {
			ErrorResponse errorRes = new ErrorResponse();
			errorRes.setTitle("System is not responding ! Please try again");
			errorRes.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			errorRes.setHttpStatusValue(HttpStatus.UNPROCESSABLE_ENTITY);
			
			throw new CovidAppException(errorRes);
		}
	}
	
	
	
	public Object getManagerList() throws Exception {
		List<ManagerDTO> managerDTOList = managerService.getManagerList();
		return ObjectUtils.isEmpty(managerDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<List>(managerDTOList, HttpStatus.OK);
		
	}
	
	
	public  Object handleUpdMgrReq(Object request, BindingResult result, String... pathParam) throws Exception {
		ManagerDTO mgrDTO = (ManagerDTO) request;
		
		if(!managerService.validateData(mgrDTO)) {
			ErrorResponse errorRes = new ErrorResponse();
			errorRes.setTitle("Manager IDs are not valid! Please confirm again.");
			errorRes.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			errorRes.setHttpStatusValue(HttpStatus.UNPROCESSABLE_ENTITY);
			
			throw new CovidAppException(errorRes);
		}
		
		int res = managerService.updateUserManager(mgrDTO);
		if(res > 0) {
			return new ResponseEntity<String>("User Information Update Successfully", HttpStatus.OK);
		}else {
			ErrorResponse errorRes = new ErrorResponse();
			errorRes.setTitle("System ERROR!!!! Please try again");
			errorRes.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			errorRes.setHttpStatusValue(HttpStatus.UNPROCESSABLE_ENTITY);
			
			throw new CovidAppException(errorRes);
		}
	}
	
	
	
	
}
