import java.util.Random;

public class OTPGenerator {
	public static String Generate()
	{
		// create instance of Random class 
        Random random = new Random(); 
  
        // Generate random integers in range 0 to 9999 
		String otpCode = String.format("%04d", random.nextInt(10000));
		
		return otpCode;
	}
}
