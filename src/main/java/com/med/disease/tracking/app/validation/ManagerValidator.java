package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.ManagerDTO;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.util.ValidationUtil;


public class ManagerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ManagerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ManagerDTO managerDTO = (ManagerDTO) target;
		
//		ValidationUtil.validateFieldRequired(Constant.Field.USER_NAME, userDTO.getUserName(), errors);
//		ValidationUtil.alphaNumericValidation(Constant.Field.USER_NAME, userDTO.getUserName(), errors);
		
//		ValidationUtil.validateFieldRequired(Constant.Field.UID, userDTO.getUid(), errors);
//		ValidationUtil.alphaNumericValidation(Constant.Field.UID, userDTO.getUid(), errors);
		
		ValidationUtil.validateFieldRequired(Constant.Field.FIRST_NAME, managerDTO.getMgrFirstName(), errors);
		ValidationUtil.simpleStringValidation(Constant.Field.FIRST_NAME, managerDTO.getMgrFirstName(), errors);
		
//		ValidationUtil.simpleStringValidation(Constant.Field.MIDDLE_NAME, userDTO.getMiddleName(), errors);
//		
		ValidationUtil.validateFieldRequired(Constant.Field.LAST_NAME, managerDTO.getMgrLastName(), errors);
		ValidationUtil.simpleStringValidation(Constant.Field.LAST_NAME,  managerDTO.getMgrLastName(), errors);
		
		ValidationUtil.validateFieldRequired(Constant.Field.TEAM, managerDTO.getTeamName(), errors);
		ValidationUtil.relaxedString(Constant.Field.TEAM,  managerDTO.getTeamName(), errors);
		
		
		
	}

}
