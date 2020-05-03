package com.med.disease.tracking.app.dto;

import java.util.Date;

public class UserDTO {
	private String userName;
	private String uid;
	private String password;
	private boolean enabled;
	private String firstName;
	private String lastName;
	private String middleName;
	private String mobile;
	private String token;
	private String role;
	private String workLocation;

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	private Date createdDtm;


	public Date getCreatedDtm() {
		return createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public UserDTO() {
	}

	public UserDTO(String userName, String password, boolean enabled) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
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

	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
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
		return "UserDTO [userName=" + userName + ", uid=" + uid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", mobile=" + mobile + ", role=" + role
				+ ", workLocation=" + workLocation + "]";
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

}
