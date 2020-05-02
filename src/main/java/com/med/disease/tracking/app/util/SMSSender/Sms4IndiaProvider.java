// required imports
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.net.URLEncoder;

public class Sms4IndiaProvider {
    // required variables
    static String url = "https://www.sms4india.com";
    
    /**
     * @param senderId 
     * @param token
     */
    public static String createSenderId(String senderId, String apiKey,String secretKey,String usetype){
    	try{
            // construct data
          JSONObject urlParameters = new JSONObject();
          urlParameters.put("apikey", apiKey);
          urlParameters.put("secret",secretKey);
          urlParameters.put("usetype",usetype);
          urlParameters.put("senderid", senderId);
          URL obj = new URL(url + "/api/v1/createSenderId");
            // send data
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
          return content.toString();
    	}catch(Exception ex){
    		System.out.println("Exception at createSenderId():"+ex);
            return "{'status':500,'message':'Internal Server Error'}";
    	}
    }
    /**
     *
     * @param token 
     * @param phone 10 digit mobile number
     * @param message
     * @param senderId
     */
    public static String sendCampaign(String apiKey,String secretKey,String useType, String phone, String message, String senderId){
      try{
          // construct data
        JSONObject urlParameters = new JSONObject();
        urlParameters.put("apikey",apiKey);
        urlParameters.put("secret",secretKey);
        urlParameters.put("usetype",useType);
        urlParameters.put("phone", phone);
        urlParameters.put("message", URLEncoder.encode(message,"UTF-8"));
        urlParameters.put("senderid", senderId);
        URL obj = new URL(url + "/api/v1/sendCampaign");
          // send data
        HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
        httpConnection.setDoOutput(true);
        httpConnection.setRequestMethod("POST");
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
        return content.toString();
      }
      catch(Exception ex){
    	System.out.println("Exception at:" + ex);
        return "{'status':500,'message':'Internal Server Error'}";
      }
        
    }
    
    /**
     * @param token
     */
     public static String checkBalance(String apiKey,String secretKey,String useType) {
     try{
         //construct data
         JSONObject urlParameters = new JSONObject();
         urlParameters.put("apikey",apiKey);
         urlParameters.put("secret",secretKey);
         urlParameters.put("usetype",useType);
         URL obj = new URL(url + "/api/v1/checkBalance");
         // send data
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
         return content.toString();
     	}catch(Exception ex){
     		System.out.println("Exception at checkBalance():"+ex);
             return "{'status':500,'message':'Internal Server Error'}";
     	}
         
     }
}
