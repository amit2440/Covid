package com.med.disease.tracking.app.dto;

public class OptionDTO {

	private Integer optionId;
	private String displayName;
	private Boolean checked;
	private Integer size;
	private Integer risk;

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
