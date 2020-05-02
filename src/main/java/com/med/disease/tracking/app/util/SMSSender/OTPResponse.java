
public class OTPResponse {
	public String Status;
	public String Message;
	public String SmsCost;
	public String BalanceAmount;
	
	public Boolean IsSuccess()
	{
		return this.Status.contentEquals("success");
	}
}
