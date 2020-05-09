package com.med.disease.tracking.app.dto.request;

import java.util.List;

public class QuestionRequestDTO {
	/** questionId */
	private Integer questionId;
	/** question */
	private String question;
	/** question */
	private String control;
	/** options */
	private List<OptionRequestDTO> options;

	/**
	 * @return the options
	 */
	public List<OptionRequestDTO> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<OptionRequestDTO> options) {
		this.options = options;
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
}
