package com.med.disease.tracking.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.dto.UserDTO;
import com.med.disease.tracking.app.util.ValidationUtil;


public class UserInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserDTO userDTO = (UserDTO) target;
		
		if(!"".equals(userDTO.getFirstName()) && userDTO.getFirstName()!=null) {
			ValidationUtil.relaxedString(Constant.Field.FIRST_NAME, userDTO.getFirstName(), errors);
		}
		
		if(!"".equals(userDTO.getLastName()) && userDTO.getLastName()!=null) {
			ValidationUtil.relaxedString(Constant.Field.LAST_NAME, userDTO.getLastName(), errors);
		}
		
		
//		ValidationUtil.validateFieldRequired(Constant.Field.MOBILE, userDTO.getMobile(), errors);
		if(!"".equals(userDTO.getMobile()) && userDTO.getMobile()!=null) {
			ValidationUtil.validateMobileNumber(Constant.Field.MOBILE, userDTO.getMobile(), errors);
		}
		
		if(!"".equals(userDTO.getRole()) && userDTO.getRole()!=null) {
			ValidationUtil.simpleStringValidation(Constant.Field.ROLE, userDTO.getRole(), errors);
			if(!userDTO.getRole().isEmpty()) {
				if(!userDTO.getRole().equals("USER") && !userDTO.getRole().equals("ADMIN") && !userDTO.getRole().equalsIgnoreCase("MANAGER")) {
					errors.rejectValue(Constant.Field.ROLE, "role.error", new Object[] { Constant.Field.ROLE }, null);
				}
			}
		}
		
		
	}

}
