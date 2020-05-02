import java.lang.*;
import java.util.*;
import org.json.JSONObject;

public class HelloWorld {

	public static void main(String[] args) {
		
        // Generate random integers in range 0 to 9999 
		String otpCode = OTPGenerator.Generate();
		
		String message = "4 digit OTP for QUESTN App - " + otpCode;
		
		// TODO: Replace with mobile number of user from api/database
		// Also verify that mobile number is valid, is 10-digit long and is stored in db
		String mobileNumber = "10-digit-mobile-number";
		
		OTPResponse otpResponse = OTPSender.Send(message, mobileNumber);
		
		if(otpResponse.IsSuccess())
		{
			// Save OTP value to DB against User record
		}
		else
		{
			// Log Error Message to DB and notify Admin
		}
	}

}
