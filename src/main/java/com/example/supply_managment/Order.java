package com.example.supply_managment;

import java.sql.ResultSet;
public class Order {
    /* order class execute the order and update in database
     for the product that has been bought by user
     * if product is already present in cart the update the quantity of particular product and if product is not present in cart database then create new order and update.
     */
    public boolean placeOrder(String customerEmail, Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String orderPresent =String.format("Select * FROM orders where customer_id = (SELECT customerId FROM customer WHERE email = '%s') and product_id = '%d",customerEmail,product.getId());
        boolean present = true;
        try{
            ResultSet rs =databaseConnection.getQueryTable(orderPresent);
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
}
