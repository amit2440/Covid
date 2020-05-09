package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.util.ValidationUtil;


public class UserObjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO userDTO = (UserDTO) target;
		
//		ValidationUtil.validateFieldRequired(Constant.Field.USER_NAME, userDTO.getUserName(), errors);
//		ValidationUtil.alphaNumericValidation(Constant.Field.USER_NAME, userDTO.getUserName(), errors);
		
//		ValidationUtil.validateFieldRequired(Constant.Field.UID, userDTO.getUid(), errors);
//		ValidationUtil.alphaNumericValidation(Constant.Field.UID, userDTO.getUid(), errors);
		
		ValidationUtil.validateFieldRequired(Constant.Field.FIRST_NAME, userDTO.getFirstName(), errors);
		ValidationUtil.simpleStringValidation(Constant.Field.FIRST_NAME, userDTO.getFirstName(), errors);
		
//		ValidationUtil.simpleStringValidation(Constant.Field.MIDDLE_NAME, userDTO.getMiddleName(), errors);
//		
		ValidationUtil.validateFieldRequired(Constant.Field.LAST_NAME, userDTO.getLastName(), errors);
		ValidationUtil.simpleStringValidation(Constant.Field.LAST_NAME, userDTO.getLastName(), errors);
		
		
		ValidationUtil.validateFieldRequired(Constant.Field.MOBILE, userDTO.getMobile(), errors);
		ValidationUtil.validateMobileNumber(Constant.Field.MOBILE, userDTO.getMobile(), errors);
		
		ValidationUtil.validateFieldRequired(Constant.Field.ROLE, userDTO.getRole(), errors);
		ValidationUtil.simpleStringValidation(Constant.Field.ROLE, userDTO.getRole(), errors);
		if(!userDTO.getRole().isEmpty()) {
			if(!userDTO.getRole().equals("USER") && !userDTO.getRole().equals("ADMIN")) {
				errors.rejectValue(Constant.Field.ROLE, "role.error", new Object[] { Constant.Field.ROLE }, null);
			}
		}
		
	}

}
