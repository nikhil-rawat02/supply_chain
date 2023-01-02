package com.example.supply_managment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDetails {
    /* CustomerDetails class contains data  like check email and
    *  password of user that need to fetch from database
    */
    DatabaseConnection dBcon = new DatabaseConnection();
    public boolean checkEmailInDB(String email){
        /* checkEmailInDB method search for user email
        *  in database and return in boolean
        */
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s'",email);
        try{
            ResultSet rs = dBcon.getQueryTable(query);
            if(rs != null && rs.next())return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean customerLogin(String email, String password){
        /* customerLogin method check and return if provided email
        *  and password match with email and password in database.
        */
        String query = String.format("SELECT * FROM CUSTOMER WHERE email =  '%s' AND password = '%s'", email,password);
        try {
            ResultSet rs = dBcon.getQueryTable(query);
            if(rs != null && rs.next())return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String getUserNumber(String email){
        /* getUserNumber method return the mobile number of user from database */
        String query = String.format("SELECT mobile FROM supply_chain.customer WHERE email = '%s'",email);
        try {
            ResultSet rs = dBcon.getQueryTable(query);
            while(rs != null && rs.next()){
                return rs.getString("mobile");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean getUserAccessCode(String email){
        /* getUserAccessCode return where user has admin right or not from database
        *  if level in db is 1 the user has admin right else not
        */
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s' AND level = 1",email);
        try {
            ResultSet rs = dBcon.getQueryTable(query);
            if(rs != null && rs.next())
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
