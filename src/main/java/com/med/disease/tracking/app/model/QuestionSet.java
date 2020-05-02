package com.med.disease.tracking.app.model;

import java.time.LocalDate;

public class QuestionSet {
	/** question set id */
	private Integer setId;
	/** description */
	private String description;
	/** created */
	private LocalDate created;
	/** isActive */
	private Boolean isActive;
	
	/**
	 * @return the setId
	 */
	public Integer getSetId() {
		return setId;
	}
	/**
	 * @param setId the setId to set
	 */
	public void setSetId(Integer setId) {
		this.setId = setId;
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
