package com.med.disease.tracking.app.model;


public class User {

	private Integer userId;
	private String firstName;
	private String lastName;
	private Boolean isActive;
	private Integer mobile;
	private String role;
	private String token;

	public User(){}

	public User(String firstName, String lastName, Boolean isActive){
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
