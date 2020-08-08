package com.med.disease.tracking.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SurveyFeedbackDTO {
	private UserDTO manager;
	private Long allowedEpassCount;
	private List<UserDTO> users;

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public UserDTO getManager() {
		return manager;
	}

	public void setManager(UserDTO manager) {
		this.manager = manager;
	}

	public Long getAllowedEpassCount() {
		return allowedEpassCount;
	}

	public void setAllowedEpassCount(Long allowedEpassCount) {
		this.allowedEpassCount = allowedEpassCount;
	}
}