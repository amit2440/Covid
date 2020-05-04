package com.med.disease.tracking.app.handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.EmptyResponseDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.service.RegisterEmployeeService;
import com.med.disease.tracking.app.service.impl.RegisterEmployeeServiceImpl;
import com.med.disease.tracking.app.util.ErrorUtil;
import com.med.disease.tracking.app.validation.UserObjectValidator;

@RequestScope
@Component
public class UserRegistrationHandler extends RestControllerHandler {

	private static final Logger logger = LoggerFactory.getLogger(RegisterEmployeeServiceImpl.class);

	@Autowired
	RegisterEmployeeService registerEmployeeService;

	private UserDTO userDTO;

	@Override
	protected void prepareRequest(Object request, BindingResult result, String... pathParam) {
		// TODO Auto-generated method stub
		this.userDTO = (UserDTO) request;
		this.bindingResult = result;
	}

	@Override
	protected void validateRequest() {
		// TODO Auto-generated method stub
		Validator validator = new UserObjectValidator();
		validator.validate(userDTO, bindingResult);
		ErrorUtil.processError(bindingResult, Constant.Module.QUESTION_FETCH);
	}

	@Override
	protected Object processRequest() throws Exception {
		// TODO Auto-generated method stub
		String successMsg = "";
		int res = registerEmployeeService.registerEmployee(userDTO);
		if (res <= 0) {
			userDTO = null;
			
		} else {
			successMsg = "User Successfully Created into System , with details " + userDTO.toString();
		}
		return ObjectUtils.isEmpty(res) ? new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK)
				: new ResponseEntity<String>(successMsg, HttpStatus.OK);

	}

	public final Object processCvsRequest(InputStream inputStream, BindingResult bindingResult) throws Exception {
		String successMsg = "";
		List<String> failUploadList = null;
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			int i = 0;
			while (reader.ready()) {
				if (i == 0) {
					String line = reader.readLine();
				} else {
					String line = reader.readLine();
					if (!"".equals(line)) {

						UserDTO user = this.getUSerObject(line);
						if(bindingResult==null) {
							bindingResult = new BeanPropertyBindingResult(user, "");
						}
						Object object = this.handle(user, bindingResult);
						if (object == null) {
							if (failUploadList == null) {
								failUploadList = new ArrayList<String>();
							}
							failUploadList.add("Error is data ---> \n" + line);
						}

//						logger.info(line);
//						int res = registerEmployeeService.registerEmployee(user);

					}
				}

				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  ObjectUtils.isEmpty(failUploadList) ?  new ResponseEntity<String>(successMsg, HttpStatus.OK)
		 :new ResponseEntity<EmptyResponseDTO>(new EmptyResponseDTO(), HttpStatus.OK);
	}

	

	protected UserDTO getUSerObject(String csvLine) {

		UserDTO user = null;
		if (!"".equals(csvLine) && csvLine.length() > 0) {
			String[] userArr = csvLine.split(",");
			user = new UserDTO();
			user.setFirstName(userArr[0] != null ? userArr[0] : null);
			user.setLastName(userArr[1] != null ? userArr[1] : null);
			user.setMobile(userArr[2] != null ? userArr[2] : null);
			user.setEnabled(userArr[3].equalsIgnoreCase("Y") ? true : false);
			user.setToken(null);
			user.setRole(userArr[4] != null ? userArr[4] : null);
			user.setWorkLocation(userArr[5] != null ? userArr[5] : null);
		}
		return user;

	}
}
