package com.example.supply_managment;

import java.sql.*;
public class DatabaseConnection {
    /* DatabaseConnection class provide connection to
    database and data from database
    */
    private static final String databaseurl = "jdbc:mysql://localhost:3306/supply_chain";
    public static final String user = "root";
    private  static final String password = "Rawat@123";
    public static Statement getStatement(){
        /* getStatement method provide the connection between database MySQL and IDE */
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
    public ResultSet getQueryTable(String query){
        /* getQueryTable method provide data from database if the form of Result set */
        Statement statement = getStatement();
        try {
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public int executeUpdateQuery(String query){
        /* executeUpdateQuery method perform CURD operation in database
        *  and return integer if return is 1 query has been executed and if
        *  return is 0 query not updated.
        */
        Statement statement = getStatement();
        try {
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  -1;
    }
}
