package com.med.disease.tracking.app.exception;

import java.sql.SQLException;

@SuppressWarnings("serial")
public class DatabaseException extends SQLException {
	
	private String errorMessage;
	
	public DatabaseException(){
		super();
	}
	
	public DatabaseException(String errorMessage){
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
