package com.med.disease.tracking.app.util;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

public class ValidationUtil {
	public static void validateFieldRequired(String errorField, Object value, Errors errors) {
		if (ObjectUtils.isEmpty(value)) {
			errors.rejectValue(errorField, "fieldName.required", new Object[] { errorField }, null);
		}
	}

	public static void validateMobileNumber(String errorField, Object value, Errors errors) {
		if (!ObjectUtils.isEmpty(value)) {
			String regx = "(\\+91)?(-)?\\s*?(91)?\\s*?(\\d{3})-?\\s*?(\\d{3})-?\\s*?(\\d{4})";
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "mobile.number.invalid", new Object[] { errorField }, null);
			}
		}
	}
	
	public static void simpleStringValidation(String errorField, Object value, Errors errors) {
//		String regx = "^([a-zA-Z]{2,}\\\\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\\\\s?([a-zA-Z]{1,})?)";
		String regx = "[a-zA-Z]{3,30}";
		if (!ObjectUtils.isEmpty(value)) {
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "only.string.allowed", new Object[] { errorField }, null);
			}
		}
	}
	
	public static void alphaNumericValidation(String errorField, Object value, Errors errors) {
		String regx = "^[a-zA-Z0-9_]*$";
		if (!ObjectUtils.isEmpty(value)) {
			String mobileNumber = (String) value;
			if (!mobileNumber.matches(regx)) {
				errors.rejectValue(errorField, "no.specialCharacter.validation", new Object[] { errorField }, null);
			}
		}
	}
}
