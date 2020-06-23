package com.med.disease.tracking.app.util.otp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OTPSender {
	
	@Value("${covid.app.authkey}")
	private  String AUTHKEY;
	
	@Value("${covid.app.templateId}")
	private String TEMPLATE_ID;
	
	@Value("${covid.app.otpLength}")
	private String OTP_LENGHT;
	
	
	
	
	public static OTPResponse send(String message, String mobileNumber) {
		String apiKey = "0LG3UZ2JTDY0JUSHP103FMN7TXP621RD";
		String secretKey = "L7BTZ1KKU3LR7KDN";
		String useType = "prod";
		String phone = mobileNumber;
		String senderId = "QUESTN";

		// String currentBalance = Sms4IndiaProvider.checkBalance(apiKey, secretKey,
		// useType);
		// JSONObject balanceObj = new JSONObject(currentBalance);
		// String createSenderResponse = OTPSender.createSenderId(senderId, apiKey,
		// secretKey, useType);

		String sendCampaignResponse = Sms4IndiaProvider.sendCampaign(apiKey, secretKey, useType, phone, message,
				senderId);
		JSONObject campaignResponseObject = new JSONObject(sendCampaignResponse);

		OTPResponse otpResponse = new OTPResponse();
		otpResponse.Status = campaignResponseObject.get("status").toString();
		otpResponse.Message = campaignResponseObject.get("message").toString();
		if (otpResponse.isSuccess()) {
			otpResponse.SmsCost = campaignResponseObject.get("smscost").toString();
			otpResponse.BalanceAmount = campaignResponseObject.get("balacne").toString();
		}
		return otpResponse;
	}
	
	
	
	public static OTPResponse sendOtpMsg91(String message, String mobileNumber,String authkey,String templatId,String otpLen) throws Exception {
		
		JSONObject urlParameters = new JSONObject();
		urlParameters.put("authkey", authkey);
		urlParameters.put("template_id", templatId);
		urlParameters.put("mobile", mobileNumber);
		urlParameters.put("otp", message);
		urlParameters.put("otp_length",otpLen);
		URL obj = new URL("https://api.msg91.com/api/v5/otp");
		HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setRequestMethod("POST");
		httpConnection.setRequestProperty("Content-Type", "application/json");
		DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
		wr.write(urlParameters.toString().getBytes());
		// get the response
		BufferedReader bufferedReader = null;
		if (httpConnection.getResponseCode() == 200) {
			bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
		} else {
			bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
		}
		StringBuilder content = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			content.append(line).append("\n");
		}
		bufferedReader.close();
		
		JSONObject campaignResponseObject = new JSONObject(content.toString());

		OTPResponse otpResponse = new OTPResponse();
		otpResponse.Status = campaignResponseObject.get("type").toString();
		otpResponse.Message = campaignResponseObject.get("request_id").toString();
		if (otpResponse.isSuccess()) {
//			otpResponse.SmsCost = campaignResponseObject.get("smscost").toString();
//			otpResponse.BalanceAmount = campaignResponseObject.get("balacne").toString();
		}
		return otpResponse;
		
//		return content.toString();
//		return true;
	}
	
	
}