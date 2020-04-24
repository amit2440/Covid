package com.med.disease.tracking.app.model;

public class User {
	private String userName;
	private String password;
	private boolean isActive;
	
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
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", isActive=" + isActive + "]";
	}
	
}
