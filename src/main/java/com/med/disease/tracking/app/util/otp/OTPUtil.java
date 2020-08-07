package com.med.disease.tracking.app.util.otp;

public class OTPUtil {

	public static void main(String[] args) {
		// Generate random integers in range 0 to 9999
		String otpCode = OTPGenerator.generate();
		String message = "4 digit OTP for QUESTN App - " + otpCode;
		String mobileNumber = "10-digit-mobile-number";

//		OTPResponse otpResponse = OTPSender.send(message, mobileNumber);

//		if (otpResponse.isSuccess()) {
//			// Save OTP value to DB against User record
//		} else {
//			// Log Error Message to DB and notify Admin
//		}
	
				try {
					OTPSender.sendSMSMsg91("332229At9RCwHN7FX5ee3335dP1",
							"5f2be100d6fc052017176eb2"
							, "9860277981", "XXXXX", "CCCCC", "TSYSIN");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
	public static String sendOtp(String mobileNumber) {

		// Generate random integers in range 0 to 9999
//		String otpCode = OTPGenerator.generate();
		String otpCode = "1234";
//		String message = "4 digit OTP for COVID-APP is " + otpCode;
//		String mobileNumber = "10-digit-mobile-number";
//uncomment below code to enable sending otp
//		OTPResponse otpResponse = OTPSender.send(message, mobileNumber);
//
//		if (!otpResponse.isSuccess()) {
//			// Save OTP value to DB against User record
//			otpCode = "0";
//		} 
		return otpCode;
	}
	
	public static String sendOtp(String mobileNumber,String authkey,String templatId,String otpLen) {

		// Generate random integers in range 0 to 9999
		String otpCode = OTPGenerator.generate();
		String message = "4 digit OTP for COVID-APP is " + otpCode;
		OTPResponse otpResponse;
		try {
			OTPSender o = new OTPSender();
			otpResponse = o.sendOtpMsg91(otpCode, mobileNumber,authkey,templatId,otpLen);
			if (!otpResponse.isSuccess()) {
				otpCode = "0";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return otpCode;
	}
	
	
	public static String sendSMSMsg91(String authkey,String flow_id,String mobileNumber,String fromDate, String toDate,String sender)  {

		// Generate random integers in range 0 to 9999
		String otpCode = OTPGenerator.generate();
		String message = "Dear xxxxxx"+ mobileNumber.substring(mobileNumber.length() - 4)+", EPass is allowed to you by your Manager.  Enjoy working from office.! " + otpCode;
		OTPResponse otpResponse;
		try {
			OTPSender o = new OTPSender();
//			otpResponse = o.sendOtpMsg91(message, mobileNumber,authkey,templatId,otpLen);
			 OTPSender.sendSMSMsg91(authkey, flow_id, mobileNumber, fromDate, toDate, sender);
//			if (!otpResponse.isSuccess()) {
//				otpCode = "0";
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return otpCode;
	}
}
