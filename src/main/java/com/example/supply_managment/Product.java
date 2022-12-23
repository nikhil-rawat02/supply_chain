package com.example.supply_managment;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleStringProperty productDetails;
    private SimpleStringProperty seller;
    private SimpleIntegerProperty quantity;

    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public double getPrice() {
        return price.get();
    }
    public String getProductDetails(){return productDetails.get();}
    public String getSellerName(){return seller.get();}
    public int getQuantity(){return quantity.get();}
/*
    public Product(int id, String name, double price) {
        // to get product on simple table
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);

    }
*/

    public Product( int id, String name, double price, String productDetails, String seller, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.productDetails = new SimpleStringProperty(productDetails);
        this.seller = new SimpleStringProperty(seller);
        this.quantity = new SimpleIntegerProperty(quantity);

    }
 public static ObservableList<Product> getProductByName(String productName) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = String.format("SELECT * FROM PRODUCT WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try {
            ResultSet rs = databaseConnection.getQuerryTable(selectProducts);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("Product_Details"),
                                rs.getString("supplier"),
                                rs.getInt("qantity")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static ObservableList<Product> getAllProducts() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = "SELECT * FROM PRODUCT";
        try {
            ResultSet rs = databaseConnection.getQuerryTable(selectProducts);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("Product_Details"),
                                rs.getString("supplier"),
                                rs.getInt("qantity")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    /*
    //orignal

    public static ObservableList<Product> getProductByName(String productName) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = String.format("SELECT * FROM PRODUCT WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        try {
            ResultSet rs = databaseConnection.getQuerryTable(selectProducts);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    public static ObservableList<Product> getAllProducts() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String selectProducts = "SELECT * FROM PRODUCT";
        try {
            ResultSet rs = databaseConnection.getQuerryTable(selectProducts);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

     */
}
