package com.med.disease.tracking.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponse {
	private String title;
	private int status;
	
	@JsonIgnore
	private HttpStatus httpStatusValue;
	private String detail;
	List<SubErrorResponse> errors = new ArrayList<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public HttpStatus getHttpStatusValue() {
		return httpStatusValue;
	}
	public void setHttpStatusValue(HttpStatus httpStatusValue) {
		this.httpStatusValue = httpStatusValue;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<SubErrorResponse> getErrors() {
		return errors;
	}
	public void setErrors(List<SubErrorResponse> errors) {
		this.errors = errors;
	}
}
