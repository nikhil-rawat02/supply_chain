package com.example.supply_managment;

import java.sql.*;

public class DatabaseConnection {
    private static final String databaseurl = "jdbc:mysql://localhost:3306/supply_chain";
    public static final String user = "root";
    private  static final String password = "Rawat@123";

    public static Statement getStatement(){
        Statement statement= null;
        Connection conn;
        try{
            conn = DriverManager.getConnection(databaseurl,user,password);
            statement = conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }
    public ResultSet getQuerryTable(String query){
        Statement statement = getStatement();
        try {

                return statement.executeQuery(query);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public int executeUpdateQuery(String query){
        Statement statement = getStatement();
        try {

            return statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  -1;
    }
    public String getUserNumber(String email){
        String query = String.format("SELECT mobile FROM supply_chain.customer WHERE email = '%s'",email);
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs != null && rs.next()){
                return rs.getString("mobile");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean getUserAccessCode(String email){
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s' AND level = 1",email);
        Statement statement = getStatement();
        try {
            ResultSet rs = statement.executeQuery(query);
            if(rs != null && rs.next())
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static void main(String[] args) {

        // testing database connection

//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        ResultSet rs = databaseConnection.getQuerryTable("SELECT * FROM CUSTOMER");
//        try {
//            while(rs.next()){
//                System.out.println(rs.getString("email") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("customerId") + " " + rs.getString("mobile") + " " + rs.getString("password"));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
