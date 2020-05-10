package com.med.disease.tracking.app.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.med.disease.tracking.app.dto.UserDTO;

public interface RegisterEmployeeService {

	public int registerEmployee(UserDTO user);

	public int registerEmpFormCSV(String line);
	
	public int updateUserOTP(String mobile,String otp);
	
	public BindingResult verifyMobile(String mobile,BindingResult errors);

}
