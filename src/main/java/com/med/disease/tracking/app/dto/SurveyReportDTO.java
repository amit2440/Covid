package com.med.disease.tracking.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SurveyReportDTO {
	private UserDTO admin;
	public UserDTO getAdmin() {
		return admin;
	}

	public void setAdmin(UserDTO admin) {
		this.admin = admin;
	}

	private List<SurveyFeedbackDTO> feedbacks;

	public List<SurveyFeedbackDTO> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<SurveyFeedbackDTO> feedbacks) {
		this.feedbacks = feedbacks;
	}
}