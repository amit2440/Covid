package com.med.disease.tracking.app.dto.request;

public class OptionRequestDTO {
	/** optionId */
	private Integer optionId;
	/** question */
	private String displayName;
	/** risk */
	private String risk;
	/** checked */
	private Boolean checked;
	
	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	/**
	 * @return the optionId
	 */
	public Integer getOptionId() {
		return optionId;
	}
	/**
	 * @param optionId the optionId to set
	 */
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the risk
	 */
	public String getRisk() {
		return risk;
	}
	/**
	 * @param risk the risk to set
	 */
	public void setRisk(String risk) {
		this.risk = risk;
	}
}
