package com.example.supply_managment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.spec.ECField;
import java.sql.ResultSet;

public class Order {
    public boolean placeOrder(String customerEmail, Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        // check and return rowCount if order already exist or not is not the insert new else update quantity
        String orderPresent =String.format("Select * FROM orders where customer_id = (SELECT customerId FROM customer WHERE email = '%s') and product_id = '%d",customerEmail,product.getId());
        boolean present = true;
        try{
            ResultSet rs =databaseConnection.getQuerryTable(orderPresent);
            if(rs == null && rs.next())present =false;
        }catch (Exception e){
            e.printStackTrace();
        }
        if (present){
            String query =String.format("INSERT INTO orders (customer_id, product_id,quantity,order_date) VALUES ((SELECT customerId FROM customer WHERE email = '%s'),%d,1,now())",customerEmail,product.getId());
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

    public boolean placeAllOrderFromCart(String customerEmail, Product product){ // this function used when cart is open and clicked on buy now
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("INSERT INTO orders (customer_id,product_id,quantity,product_status) VALUES((SELECT customerId FROM customer WHERE email = '%s'), %d ,(SELECT product_quantity_incart FROM cart WHERE product_ID = %d), 'Order Placed');",customerEmail,product.getId());
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
        String query = String.format("SELECT * FROM product WHERE productId = ( SELECT product_id FROM orders where customer_id = ( SELECT customerId FROM customer where email = '%s'))",email);
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
