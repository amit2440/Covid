package com.med.disease.tracking.app.util;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

public class ValidationUtil {
	public static void validateFieldRequired(String errorField, Object value, Errors errors) {
		if (ObjectUtils.isEmpty(value)) {
			errors.rejectValue(errorField, "fieldName.required", new Object[] {errorField}, null);
		}
	}
}
