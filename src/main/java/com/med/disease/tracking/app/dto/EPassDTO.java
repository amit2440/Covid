package com.med.disease.tracking.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EPassDTO {
	private UserDTO user;
	private SurveyDTO survey;
	private Boolean isAllowed;
	private LocalDate toDate;
	private UserDTO createdBy;
	private LocalDateTime createdOn;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public SurveyDTO getSurvey() {
		return survey;
	}

	public void setSurvey(SurveyDTO survey) {
		this.survey = survey;
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

	public UserDTO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
}
