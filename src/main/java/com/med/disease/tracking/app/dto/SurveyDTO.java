package com.med.disease.tracking.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SurveyDTO {
	/** question set id */
	private Integer surveyId;
	/** description */
	private String description;
	/** created */
	private LocalDate created;
	/** isActive */
	private Boolean isActive;
	/** questions */
	private List<QuestionDTO> questions;
	
	/**
	 * @return the questions
	 */
	public List<QuestionDTO> getQuestions() {
		return questions;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
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
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the created
	 */
	public LocalDate getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
