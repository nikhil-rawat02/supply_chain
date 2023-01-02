package com.example.supply_managment;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.sql.ResultSet;

public class Cart {
    /* Cart class used to gather all the product that has been added by user in cart
    so that user can purchase all selected product at once.
     */
    public static  TableView<Cart> productTableView;
    ObservableList <Cart> products;
    DatabaseConnection dBCon = new DatabaseConnection();
    private SimpleStringProperty name;
    private SimpleStringProperty productDetails;
    private SimpleIntegerProperty productId;
    private SimpleStringProperty supplier;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty product_quantity_incart;
    public String getName() {return name.get();}
    public SimpleStringProperty nameProperty() {return name;}
    public void setName(String name) {this.name.set(name);}
    public int getProductId() {return productId.get();}
    public SimpleIntegerProperty productIdProperty() {return productId;}
    public void setProductId(int productId) {this.productId.set(productId);}
    public String getProductDetails() {return productDetails.get();}
    public SimpleStringProperty productDetailsProperty() {return productDetails;}
    public void setProductDetails(String productDetails) {this.productDetails.set(productDetails);}
    public String getSupplier() {return supplier.get();}
    public SimpleStringProperty supplierProperty() {return supplier;}
    public void setSupplier(String supplier) {this.supplier.set(supplier);}
    public double getPrice() {return price.get();}
    public SimpleDoubleProperty priceProperty() {return price;}
    public void setPrice(double price) {this.price.set(price);}
    public int getProduct_quantity_incart() {return product_quantity_incart.get();}
    public SimpleIntegerProperty product_quantity_incartProperty() {return product_quantity_incart;}
    public void setProduct_quantity_incart(int product_quantity_incart) {
        this.product_quantity_incart.set(product_quantity_incart);
    }
    Cart(){}
    public Cart(String name, String productDetails,String supplier,double price, int product_quantity_incart){
        /* Cart class constructed to create new Cart object with
        product name, product details, supplier name, product price,
        total quantity
        */
        this.name = new SimpleStringProperty(name);
        this.productDetails = new SimpleStringProperty(productDetails);
        this.supplier = new SimpleStringProperty(supplier);
        this.price = new SimpleDoubleProperty(price);
        this.product_quantity_incart = new SimpleIntegerProperty(product_quantity_incart);
    }
    public Cart getSelectedProduct(){
        /* getSelectedProduct method return the selected cart
        item from table when user has selected a product either
        to remove it from cart or proceed for buying
        */
        try {
            return productTableView.getSelectionModel().getSelectedItem();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean placeMyOrder(ResultSet rs){
        /* placeMyOrder method booked all the order that placed by user */
        boolean orderPlaced = false;
        try {
            while (rs.next()){
                int productID = rs.getInt("product_ID");
                int customerID = rs.getInt("customer_ID");
                int qty = rs.getInt("product_quantity_incart");
                    String query = String.format("INSERT INTO orders (product_id,customer_id,quantity, order_date) VALUES(%d,%d,%d,now()); ",productID,customerID,qty);
                dBCon.executeUpdateQuery(query);
                orderPlaced = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            while(rs.next()){
                int productID = rs.getInt("product_ID");
                int qty = rs.getInt("product_quantity_incart");
                String query = String.format("UPDATE product SET qantity = qantity - %d WHERE productId = %d",productID,qty);
                dBCon.executeUpdateQuery(query);
                System.out.println("removed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderPlaced;
    }
    public ResultSet getCartData(String email){
        /* getCartData method return the result set of all the
        product that has been added by user in cart and not
        purchased yet.
         */
        String query = String.format("SELECT product_ID,customer_ID,product_quantity_incart FROM product p Inner JOIN cart c on p.productId = c.product_ID AND customer_ID = (SELECT customerId FROM customer WHERE email = '%s')",email);
        try{
            return dBCon.getQueryTable(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ObservableList <Cart> getMyProduct(String email){
        ObservableList<Cart> productList = FXCollections.observableArrayList();
        String query = String.format("SELECT name,Product_Details,supplier,price,product_quantity_incart FROM product p Inner JOIN cart c on p.productId = c.product_ID AND customer_ID = (SELECT customerId FROM customer WHERE email = '%s')",email);// user inner join to get quantity
        try {
            ResultSet rs = dBCon.getQueryTable(query);
            while(rs.next()){
                productList.add(
                        new Cart(
                            rs.getString("name"),
                            rs.getString("Product_Details"),
                            rs.getString("supplier"),
                            rs.getDouble("price"),
                            rs.getInt("product_quantity_incart")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
    public boolean isPresentInDB(int productId){
        /* isPresentInDB method return if product Id id present in cart */
        String query  =String.format("SELECT * FROM cart WHERE product_ID = %d", productId);
        try {
            ResultSet rs = dBCon.getQueryTable(query);
            if(rs!=null && rs.next())return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkqty(String email,int productId){
        /* checkqty method check if quantity of selected product
        is zero or not if it is zero then delete selected product from cart database
        */
        String query = String.format("DELETE FROM cart WHERE (product_quantity_incart = 0 AND product_ID = %d AND customer_ID = (SELECT customerId FROM customer WHERE email = '%s'))",productId,email);
        ResultSet rs = dBCon.getQueryTable(query);
        try {
            if(rs != null || rs.next())return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeFromCart(String email, int productId){
        String query = String.format("UPDATE cart SET product_quantity_incart = product_quantity_incart -1 WHERE (customer_id =(SELECT customerId FROM customer WHERE email = '%s') and product_id = %d)",email,productId);
        ResultSet rs = dBCon.getQueryTable(query);
        boolean check = false;
        try {
            if(rs != null || rs.next())check = true;
            if(check)Login.updateStatus.setText("Order Removed from Cart");
        }catch (Exception e){
            e.printStackTrace();
        }
        if (check && checkqty(email,productId)){
            String removeItemQuery = String.format("DELETE FROM cart WHERE (customer_ID = (SELECT customerId FROM customer WHERE email = '%s') AND product_ID = %d AND product_quantity_incart = 0)",email,productId);
            int removed = 0;
            try{
                removed = dBCon.executeUpdateQuery(removeItemQuery);
                if(removed != 0) Login.updateStatus.setText("Order Removed from Cart");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return check;
    }
    public boolean addToCartSelectedProduct(String email, int productId){
        if(isPresentInDB(productId)){
            String query = String.format("UPDATE cart SET product_quantity_incart = product_quantity_incart +1 WHERE (customer_id =(SELECT customerId FROM customer WHERE email = '%s') and product_id = %d)",email,productId);
            int rowCount = 0;
            try {
                rowCount = dBCon.executeUpdateQuery(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            return rowCount != 0;
        }else{
            String query = String.format("INSERT INTO cart (customer_ID, product_ID) VALUES ((SELECT customerId FROM customer WHERE email = '%s'),%d)", email,productId);
            int rowCount = 0;
            try{
                rowCount = dBCon.executeUpdateQuery(query);
            }catch ( Exception e){
                e.printStackTrace();
            }
            return rowCount != 0;
        }
    }
    public Pane getOrderProduct(String email){
        /* getOrderProduct method fetch data from cart Database */
        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn details = new TableColumn<>("Product Details");
        details.setCellValueFactory(new PropertyValueFactory<>("productDetails"));
        TableColumn supplier = new TableColumn<>("Supplier");
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        TableColumn price = new TableColumn<>("Product price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn productQuantity = new TableColumn<>("Quantity");
        productQuantity.setCellValueFactory(new PropertyValueFactory<>("product_quantity_incart"));

        products = getMyProduct(email);
        productTableView = new TableView<>();
        productTableView.setItems(products);
        productTableView.getColumns().addAll(name,price,details,supplier,productQuantity);
        productTableView.setMinSize(supply_chain.width,supply_chain.height);
        productTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color : #336678;");
        tablePane.setMinSize(supply_chain.width,supply_chain.height);
        tablePane.getChildren().add(productTableView);
        return tablePane;
    }
    public void removeAllFromCart(String email){
        /* removeAllFromCart method removes all the
        data of usr from the cart after all order placed
        */
        String query = String.format("DELETE FROM cart WHERE customer_ID = (SELECT customerId FROM customer WHERE email = '%s')",email);
        try {
            dBCon.executeUpdateQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
