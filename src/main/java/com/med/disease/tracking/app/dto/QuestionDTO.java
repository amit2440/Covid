package com.med.disease.tracking.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class QuestionDTO {
	/** question id */
	private Integer questionId;
	/** question */
	private String question;
	/** control */
	private String control;
	
	/**
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the control
	 */
	public String getControl() {
		return control;
	}
	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}
}
