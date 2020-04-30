package com.med.disease.tracking.app.model;

public class User {
	private String userName;
	private String password;
	private boolean isActive;
	private Integer mobile;
	private String role;
	private String token;
	private Integer uid;
	private String firstName;
	private String lastName;

	public User() {
	}

	public User(String userName, String password, boolean isActive) {
		super();
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", isActive=" + isActive + ", mobile=" + mobile + ", role=" + role
				+ ", uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
