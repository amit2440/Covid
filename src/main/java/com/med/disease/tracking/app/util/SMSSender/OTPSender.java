import org.json.JSONObject;

public class OTPSender {
	public static OTPResponse Send(String message, String mobileNumber)
	{
		// TODO: Sunil: Please move all below keys to config file
		String apiKey = "0LG3UZ2JTDY0JUSHP103FMN7TXP621RD";
		String secretKey = "L7BTZ1KKU3LR7KDN";
		String useType = "prod";
		String phone = mobileNumber;
		String senderId = "QUESTN";
		
		//String currentBalance = Sms4IndiaProvider.checkBalance(apiKey, secretKey, useType);
		//JSONObject balanceObj = new JSONObject(currentBalance);
		//String createSenderResponse = OTPSender.createSenderId(senderId, apiKey, secretKey, useType);
		
		String sendCampaignResponse = Sms4IndiaProvider.sendCampaign(apiKey, secretKey, useType, phone, message, senderId);
		JSONObject campaignResponseObject = new JSONObject(sendCampaignResponse);
		
		OTPResponse otpResponse = new OTPResponse();
		otpResponse.Status = campaignResponseObject.get("status").toString();
		otpResponse.Message = campaignResponseObject.get("message").toString();
		if(otpResponse.IsSuccess())
		{
			otpResponse.SmsCost = campaignResponseObject.get("smscost").toString();
			otpResponse.BalanceAmount = campaignResponseObject.get("balacne").toString();
		}
		return otpResponse;
	}
}
