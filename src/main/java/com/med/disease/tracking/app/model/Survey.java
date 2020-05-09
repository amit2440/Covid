package com.med.disease.tracking.app.model;

import java.time.LocalDate;
import java.util.List;

public class Survey {
	/** question set id */
	private Integer surveyId;
	/** description */
	private String description;
	/** created */
	private LocalDate created;
	/** isActive */
	private Boolean isActive;
	/** questionsIds */
	private List<Integer> questionIds;
	/** questions */
	private List<Question> questions;
	
	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	/**
	 * @return the questionIds
	 */
	public List<Integer> getQuestionIds() {
		return questionIds;
	}
	/**
	 * @param questionIds the questionIds to set
	 */
	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
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
