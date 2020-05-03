package com.med.disease.tracking.app.model.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String uid;
	private String username;
	private String mobile;
	private List<String> roles;

	public JwtResponse(String accessToken, String id, String username, String mobile, List<String> roles) {
		this.token = accessToken;
		this.uid = id;
		this.username = username;
		this.mobile = mobile;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
}
