package com.med.disease.tracking.app.dto;

import java.time.LocalDate;

public class EPassRequestDTO {

	private Integer userId;
	private Integer surveyId;
	private Boolean isAllowed;
	private LocalDate toDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
}
