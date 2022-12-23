package com.example.supply_managment;

import java.sql.ResultSet;

public class CustomerDetails {
    public boolean checkEmailInDB(String email){
        // if empty also return true
//        if not found in DB return true
//                if found return false
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s'",email);
        try{
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet rs = databaseConnection.getQuerryTable(query);
            if(rs != null && rs.next())return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean customerLogin(String email, String password){
        String query = String.format("SELECT * FROM CUSTOMER WHERE email =  '%s' AND password = '%s'", email,password);
        try {
            DatabaseConnection dbCon = new DatabaseConnection();
            ResultSet rs = dbCon.getQuerryTable(query);
            if(rs != null && rs.next())return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
