package com.example.supply_managment;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SendOTP {
    /* SendOTP class provide the SMS authentication from user register mobile when new user try to sign up in application in the class Fast2SMS api has been used to send SMS by providing user enter mobile number OTP has been generated by using random class and otp has been encrypted using URLEncode class with message and
    * SendOTP class also provide SMS to user for order confirmation when user purchased the product
    */
    public  void sendOTP(String message,String number, String apiKey){
        /* sendOTP method construct the URL by weathering data like apiKey, user contact number, message and that URL hit to Fast2SMS rest API to send SMS to user.
        */
        try{
            String sendId = "TXTIND";
            String route = "v3";
            message = URLEncoder.encode(message, StandardCharsets.UTF_8);
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&sender_id=" + sendId +"&message=" + message +"&route=" + route+"&numbers=" + number;

            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestProperty("cache-control" , "no-cache");
            System.out.println("Wait.........");
            int responseCode = con.getResponseCode();
            System.out.println("ResponseCode" + responseCode);
            StringBuffer response = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true){
                String line = br.readLine();
                if(line == null)break;
                response.append(line);
            }
            System.out.println(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String generateOTP (){
        /* generateOTP method generate OTP using random class and send 6 digit long OTP */
        Random random = new Random();
        int otpLength = 6;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < otpLength; i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }
    public String signupOTP(String userNumber){
        /* signupOTP method required user contact number,
           and it sends and return OTP to user and calling
           function respectively */
        String otpMessage = generateOTP();
        System.out.println("new OTP" + otpMessage);
        String apikey ="qsTpFdRzchA1tZo7w4E6jm3XNDguIHvOGPMLxeri08anlbSUWJok3sRyTxge9NFHLME15aP0DOZdlujc";
        sendOTP("DO not share; your myShopping app signup otp is : " +otpMessage,userNumber,apikey);
        System.out.println(otpMessage);
       return otpMessage;
    }
    public void orderStatus(String userNumber, String productName){
        /* orderStatus method required user number and
           product name and send user a confirmation SMS
           of successful oder
        */
        String apikey ="qsTpFdRzchA1tZo7w4E6jm3XNDguIHvOGPMLxeri08anlbSUWJok3sRyTxge9NFHLME15aP0DOZdlujc";
        sendOTP("Your Order" + productName + " from My shopping has been placed successfully",userNumber,apikey);
    }
}
