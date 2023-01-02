package com.example.supply_managment;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class additional_function {
    /* this class is used to keep additional functionalities
    separate from the application code there are two
    functionality has been added one is validate Email and
    password encryption and password decryption
     */
    public boolean validateEmail(String email){
        /*There are different ways through which we can perform
        email validation using a regular expression.
       and here I have used The Simplest regex to validate email.
       The regular expression ^(.+)@(.+)$ is the simplest regular
       expression the checks the @ symbol only. It doesn't care
       about the characters before and after the '@' symbol.
       */
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }
    private byte [] getSHA(String input){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String getEncryptedPassword(String password){
        /* getEncryptedPassword method will convert encrypted
            password in 16 length encrypted password
             */
        try{
            BigInteger number = new BigInteger(1, Objects.requireNonNull(getSHA(password)));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public String updateInDB (String password){
        /* updateInDB method will return the password in
        encrypted from using getEncryptedPassword method
         */
        return getEncryptedPassword(password);
    }
}
