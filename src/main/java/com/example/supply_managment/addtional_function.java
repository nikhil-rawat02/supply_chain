package com.example.supply_managment;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addtional_function {
    public boolean validateEmail(String email){
        // regular expression
        String regex = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern
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
    private String getEncrptedPassword(String password){
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
        return getEncrptedPassword(password);
    }
    public static void main(String[] args) {

    }
}
