package com.med.disease.tracking.app.util.otp;

public class OTPUtil {

	public static void main(String[] args) {
		// Generate random integers in range 0 to 9999
		String otpCode = OTPGenerator.generate();
		String message = "4 digit OTP for QUESTN App - " + otpCode;
		String mobileNumber = "10-digit-mobile-number";

		OTPResponse otpResponse = OTPSender.send(message, mobileNumber);

		if (otpResponse.isSuccess()) {
			// Save OTP value to DB against User record
		} else {
			// Log Error Message to DB and notify Admin
		}
	}
}
