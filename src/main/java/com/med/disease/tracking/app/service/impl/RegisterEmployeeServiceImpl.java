package com.med.disease.tracking.app.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.repository.IUserRepository;
import com.med.disease.tracking.app.service.RegisterEmployeeService;
import com.med.disease.tracking.app.util.ValidationUtil;


@Service
public class RegisterEmployeeServiceImpl implements RegisterEmployeeService {

private static final Logger logger = LoggerFactory.getLogger(RegisterEmployeeServiceImpl.class);
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public int registerEmployee(UserDTO user) {
		// TODO Auto-generated method stub
		int res = 0;
//		Need to add validation here and some more stuff if needed like token generation logic and all
		try {
			res = userRepository.insertEmployee(user);
		}catch(Exception e) {
				logger.info(" Error while processing  : "+user+" is ===>"+e.getMessage());
		}
	
		return res;
	}
	

	@Override
	public int registerEmpFormCSV(String line) {
		// TODO Auto-generated method stub
		UserDTO user = this.getUSerObject(line);
		logger.info(line);
		int res = userRepository.insertEmployee(user);
		return res;
	}
	
	

	protected UserDTO getUSerObject(String csvLine) {

		UserDTO user = null;
		if (!"".equals(csvLine) && csvLine.length() > 0) {
			String[] userArr = csvLine.split(",");
			user = new UserDTO();
//			user.setUid(userArr[0] != null ? userArr[0] : null);
//			user.setUserName(userArr[1] != null ? userArr[1] : null);
			user.setFirstName(userArr[2] != null ? userArr[2] : null);
//			user.setMiddleName(userArr[3] != null ? userArr[3] : null);
			user.setLastName(userArr[4] != null ? userArr[4] : null);
			user.setMobile(userArr[5] != null ? userArr[5] : null);
//			user.setPassword(null);
			user.setEnabled(userArr[6].equalsIgnoreCase("Y") ? true : false);
			user.setToken(null);
			user.setRole(userArr[7] != null ? userArr[7] : null);
			user.setWorkLocation(userArr[8] != null ? userArr[8] : null);
//			user.setCreatedDtm(null);

		}
		return user;

	}


	@Override
	public int updateUserOTP(String mobile, String otp) {
		// TODO Auto-generated method stub
		
		UserDTO userDTO = new UserDTO();
		userDTO.setMobile(mobile);
		userDTO.setEnabled(true);
		userDTO.setToken(otp);
		
		return userRepository.update(userDTO);
	}


	@Override
	public BindingResult verifyMobile(String mobile,BindingResult error) {
		
		// TODO Auto-generated method stub
		if(!"".equals(mobile) && mobile!=null) {
			ValidationUtil.validateMobileNumber(Constant.Field.MOBILE, mobile, error);
			if(error.hasErrors()) {
				return error;
			}
		}
		
		Optional<UserDTO> userDTO = userRepository.findByUserName(mobile);
		if(userDTO.isEmpty()) {
			 error.rejectValue(Constant.Field.MOBILE, "mobile.number.notPresent", new Object[] { Constant.Field.MOBILE }, null);
			 return error;
		}
		return error;
	}

}
