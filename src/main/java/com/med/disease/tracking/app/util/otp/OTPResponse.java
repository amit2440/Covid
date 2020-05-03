package com.med.disease.tracking.app.util.otp;

public class OTPResponse {
	public String Status;
	public String Message;
	public String SmsCost;
	public String BalanceAmount;

	public Boolean isSuccess() {
		return this.Status.contentEquals("success");
	}
}