package com.example.supply_managment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.spec.ECField;
import java.sql.ResultSet;

public class Order {
    public boolean placeOrder(String customerEmail, Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        // check and return rowCount if order already exist or not is not the insert new else update quantity
        String orderPresent =String.format("SELECT * FROM orders where customer_id = (SELECT customerId FROM customer WHERE email = '%s') and product_id = '%d",customerEmail,product.getId());
        int present = 0;
        try{
            present = databaseConnection.executeUpdateQuery(orderPresent);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (present == 0){
            String query =String.format("INSERT INTO orders (customer_id, product_id) VALUES ((SELECT customerId FROM customer WHERE email = '%s'),%d)",customerEmail,product.getId());
            int rowCount = 0;
            try{
                rowCount = databaseConnection.executeUpdateQuery(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            return rowCount != 0;

        }else {
            String query =String.format("UPDATE orders SET quantity = quantity +1 WHERE (customer_id =(SELECT customerId FROM customer WHERE email = '%s') and product_id = %d)",customerEmail,product.getId());
            int rowCount = 0;
            try{
                rowCount = databaseConnection.executeUpdateQuery(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            return rowCount != 0;
        }
    }

    public boolean placeAllOrderFromCart(String customerEmail, Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "";
        int rowCount =0;
        try{
            rowCount = databaseConnection.executeUpdateQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowCount != 0;
    }
    public static ObservableList <Product> getOrder(String email){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String query = String.format("SELECT name,price,Product_Details,supplier FROM product INNER JOIN orders ON product_id Inner JOIN customer ON customer_id WHERE email = '%s'",email);
        try {
            ResultSet rs = databaseConnection.getQuerryTable(query);
            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("productId"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getString("Product_Details"),
                                rs.getString("supplier"),
                                rs.getInt("quantity")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
