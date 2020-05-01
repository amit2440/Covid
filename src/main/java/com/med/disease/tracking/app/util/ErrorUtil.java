package com.med.disease.tracking.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.med.disease.tracking.app.constant.Constant;
import com.med.disease.tracking.app.exception.CovidAppException;
import com.med.disease.tracking.app.exception.ErrorResponse;
import com.med.disease.tracking.app.exception.SubErrorResponse;

public class ErrorUtil {
	
	public static void processError(BindingResult bindingResult, String module) {
		MessageSource messageSource = BeanUtil.getBean(MessageSource.class);
		if(bindingResult.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			errorResponse.setHttpStatusValue(HttpStatus.UNPROCESSABLE_ENTITY);
			
			List<SubErrorResponse> subErrorResponses = new ArrayList<>();
			if(bindingResult != null) {
				bindingResult.getAllErrors().stream().filter(obj -> obj instanceof FieldError).forEach(objError -> {
					FieldError fieldError = (FieldError) objError;
					SubErrorResponse subError = new SubErrorResponse();
					subError.setField(fieldError.getField());
					subError.setMessage(messageSource.getMessage(fieldError, Locale.ENGLISH));
					subErrorResponses.add(subError);
				});	
			}
			errorResponse.setErrors(subErrorResponses);
			errorResponse.setTitle(messageSource.getMessage(Constant.Message.VALIDATION_FAILED, new Object[]{}, Locale.ENGLISH));
			errorResponse.setDetail(messageSource.getMessage(Constant.Message.VALIDATION_DETAILS, new Object[]{}, Locale.ENGLISH));
			throw new CovidAppException(errorResponse);
		}
	}
}
