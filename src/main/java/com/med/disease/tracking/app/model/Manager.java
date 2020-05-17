package com.med.disease.tracking.app.model;

public class Manager {

	public Integer mgrId;
	public String mgrFirstName;
	public String mgrLastName;
	public String teamName;
	public boolean isActive;
	public Integer newMgrId;

	public Integer getNewMgrId() {
		return newMgrId;
	}

	public void setNewMgrId(Integer newMgrId) {
		this.newMgrId = newMgrId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getMgrId() {
		return mgrId;
	}

	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrFirstName() {
		return mgrFirstName;
	}

	public void setMgrFirstName(String mgrFirstName) {
		this.mgrFirstName = mgrFirstName;
	}

	public String getMgrLastName() {
		return mgrLastName;
	}

	public void setMgrLastName(String mgrLastName) {
		this.mgrLastName = mgrLastName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "ManagerDTO [mgrId=" + mgrId + ", mgrFirstName=" + mgrFirstName + ", mgrLastName=" + mgrLastName
				+ ", teamName=" + teamName + "]";
	}

}
