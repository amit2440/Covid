package com.med.disease.tracking.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.med.disease.tracking.app.dto.response.FeedbackResponseDTO;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RiskDTO {
	private Integer userId;
	private Integer surveyId;
	private String riskLevel;
	private Boolean disableSelfAssessment = false;

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

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Boolean getDisableSelfAssessment() {
		return disableSelfAssessment;
	}

	public void setDisableSelfAssessment(Boolean disableSelfAssessment) {
		this.disableSelfAssessment = disableSelfAssessment;
	}
}