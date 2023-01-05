package com.example.supply_managment;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
public class ProductDetails {
   public static TableView<Product> productTable;
   public static ObservableList<Product> products;
    public Pane getAllProducts(){
        TableColumn<Product,Integer> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id"));
        TableColumn<Product,String> name = new TableColumn<>("Product name");
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        TableColumn<Product,Double> price = new TableColumn<>("Product price");
        price.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
        TableColumn<Product,String> details = new TableColumn<>("Product Details");
        details.setCellValueFactory(new PropertyValueFactory<Product,String>("productDetails"));
        TableColumn<Product,String> supplier = new TableColumn<>("Supplier");
        supplier.setCellValueFactory(new PropertyValueFactory<Product,String>("supplier")); //seller
        TableColumn <Product,Integer> quantity  = new TableColumn<Product,Integer>("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        products = Product.getAllProducts();
         productTable = new TableView<>();

        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,details,supplier);
        if(Login.adminLoggedIn){
            productTable.getColumns().add(quantity);
        }
        productTable.setMinSize(supply_chain.width,supply_chain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color : #336678;");
        tablePane.setMinSize(supply_chain.width,supply_chain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Pane getProductsByName(String productByName){
        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn<>("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn details = new TableColumn<>("Product_Details");
        details.setCellValueFactory(new PropertyValueFactory<Product,String>("Product_Details"));
        TableColumn supplier = new TableColumn<>("supplier");
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        TableColumn quantity = new TableColumn<>("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("qantity"));

        ObservableList<Product> products = Product.getProductByName(productByName);
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,details,supplier);
        if(Login.adminLoggedIn){
            productTable.getColumns().add(quantity);
        }
        productTable.setMinSize(supply_chain.width,supply_chain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color : #336678;");
        tablePane.setMinSize(supply_chain.width,supply_chain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Product getSelectedProduct(){
       try {
           return productTable.getSelectionModel().getSelectedItem();
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }
}
