package com.med.disease.tracking.app.model.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
//	@NotBlank
	private String username;

//	@NotBlank
	private String password;

	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
