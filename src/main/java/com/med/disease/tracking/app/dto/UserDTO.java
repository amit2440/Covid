package com.med.disease.tracking.app.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO {
	private String userName;
	private Integer userId;
	private String password;
	private Boolean isActive;
	private String firstName;
	private String lastName;
	private String mobile;
	private String token;
	private String role;
	private String workLocation;
	private Integer mgrID;
	private String riskStatus;
	private String managerName;
	private EPassDTO epass;
	
	/**
	 * @return the epass
	 */
	public EPassDTO getEpass() {
		return epass;
	}

	/**
	 * @param epass the epass to set
	 */
	public void setEpass(EPassDTO epass) {
		this.epass = epass;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	public String getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getMgrID() {
		return mgrID;
	}

	public void setMgrID(Integer mgrID) {
		this.mgrID = mgrID;
	}

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

	public UserDTO(String userName, String password, Boolean isActive) {
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

//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}

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
		return "UserDTO [userName=" + userName + ", userId=" + userId + ", firstName=" + firstName + ", lastName="
				+ lastName + ",, mobile=" + mobile + ", role=" + role + ", workLocation=" + workLocation + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	

}