package com.med.disease.tracking.app.model;

public class UserRisk {

	private Integer userId;
	private Integer managerId;
	private String riskStatus;
	private Integer surveyId;
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the riskStatus
	 */
	public String getRiskStatus() {
		return riskStatus;
	}
	/**
	 * @param riskStatus the riskStatus to set
	 */
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	/**
	 * @return the surveyId
	 */
	public Integer getSurveyId() {
		return surveyId;
	}
	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
}
