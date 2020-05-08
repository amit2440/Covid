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
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.service.UserInfoService;
import com.med.disease.tracking.app.service.impl.RegisterEmployeeServiceImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.UserInfoValidator;

@RequestScope
@Component
public class UserInfoHandler extends RestControllerHandler {

	private static final Logger logger = LoggerFactory.getLogger(RegisterEmployeeServiceImpl.class);

	@Autowired
	UserInfoService userInfoService;

	private UserDTO userDTO;
	
	private String operation;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.userDTO = (UserDTO) request;
		this.operation = pathParam[0];
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub
		Validator validator = new UserInfoValidator();
		validator.validate(userDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		// TODO Auto-generated method stub
		if(operation.equals("search")) {
			List<UserDTO> userDTOList = userInfoService.searchUser(userDTO);
			return  ObjectUtils.isEmpty(userDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
						: new ResponseEntity<List>(userDTOList, HttpStatus.OK);
		}else if(operation.equals("update")) {
			if(userInfoService.updateUser(userDTO)) {
				return new ResponseEntity<String>("User Information Update Successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK);
			}
		}else if(operation.equals("userIDSearch")) {
			List<UserDTO> userDTOList = userInfoService.searchUser(userDTO);
			return  ObjectUtils.isEmpty(userDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
						: new ResponseEntity<List>(userDTOList, HttpStatus.OK);
			
		}else {
			//if any parameter is not passed then will consider user search without any filter
			if(ObjectUtils.isEmpty(userDTO)) {
				userDTO = new UserDTO();
			}
			List<UserDTO> userDTOList = userInfoService.searchUser(userDTO);
			return  ObjectUtils.isEmpty(userDTOList) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
						: new ResponseEntity<List>(userDTOList, HttpStatus.OK);
		}
	}

}
