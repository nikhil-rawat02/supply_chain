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
    private SimpleStringProperty productDetails; //productDetails
    private SimpleStringProperty supplier; // seller
    private SimpleIntegerProperty quantity;

//    public int getId() {
//        return id.get();
//    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getProductDetails() {
        return productDetails.get();
    }

    public SimpleStringProperty productDetailsProperty() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails.set(productDetails);
    }

    public String getSupplier() {
        return supplier.get();
    }

    public SimpleStringProperty supplierProperty() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier.set(supplier);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

/*
    public Product(int id, String name, double price) {
        // to get product on simple table
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);

    }
*/

    public Product( int id, String name, double price, String productDetails, String supplier, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.productDetails = new SimpleStringProperty(productDetails);//productDetails
        this.supplier = new SimpleStringProperty(supplier); //seller
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
        String selectProducts = "SELECT * FROM product WHERE qantity != 0";
        try {
            ResultSet rs = databaseConnection.getQuerryTable(selectProducts);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("Product_Details"), //productDetails
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", productDetails='" + productDetails + '\'' +
                ", seller='" + supplier + '\'' +
                ", quantity=" + quantity +
                '}';
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
