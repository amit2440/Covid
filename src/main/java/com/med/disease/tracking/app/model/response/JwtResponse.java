package com.med.disease.tracking.app.model.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
//	private String lastName;
//	private String firstName;
//	private String username;
	private String mobile;
	private Integer userId;
	private List<String> roles;

//	public JwtResponse(String accessToken, String username, String firstName,String lastName, String mobile, List<String> roles) {
//		this.token = accessToken;
////		this.uid = id;
//		this.username = username;
//		this.mobile = mobile;
//		this.roles = roles;
//		this.lastName = lastName;
//		this.firstName = firstName;
//	}
//
//	public JwtResponse(String accessToken,Integer userId, String username, String firstName,String lastName, String mobile, List<String> roles) {
//		this.token = accessToken;
//		this.userId = userId;
//		this.username = username;
//		this.mobile = mobile;
//		this.roles = roles;
//		this.lastName = lastName;
//		this.firstName = firstName;
//	}
	
	public JwtResponse(String accessToken,Integer userId,  String mobile, List<String> roles) {
		this.token = accessToken;
		this.mobile = mobile;
		this.roles = roles;
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	

//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

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
