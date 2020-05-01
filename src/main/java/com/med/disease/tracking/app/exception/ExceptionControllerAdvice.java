package com.med.disease.tracking.app.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	private static final Logger LOGGER = Logger.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		LOGGER.error("ErrorMessage from ExceptionControllerAdvice.exceptionHandler()");
		ErrorResponse error = new ErrorResponse();
		error.setTitle("Please contact your administrator");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CovidAppException.class)
	public ResponseEntity<ErrorResponse> covidAppExceptionHandler(CovidAppException ex) {
		LOGGER.error("ErrorMessage from ExceptionControllerAdvice.covidAppExceptionHandler()");
		return new ResponseEntity<ErrorResponse>(ex.getErrorResponse(), ex.getErrorResponse().getHttpStatusValue());
	}
}
