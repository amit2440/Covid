package com.med.disease.tracking.app.model;

/**
 * 
 * This class is used to generate various reports.
 * manager ID : logged-in User Id.
 * report name : name of the report we want to pull like ... feedbackRpt, surveryRpt etc..
 * As n when we need we will need parameter and report name.
 * 
 * 
 * @author Virendra
 *
 */
public class ReportRequest {

	public Integer mgrId;
	public String reportName;
	
	
	
	public Integer getMgrId() {
		return mgrId;
	}
	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
