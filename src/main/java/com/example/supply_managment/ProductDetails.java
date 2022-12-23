package com.example.supply_managment;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
   public static TableView<Product> productTable;
/*
   public Pane getAllProducts(){
       TableColumn id = new TableColumn("id");
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       TableColumn name = new TableColumn<>("name");
       name.setCellValueFactory(new PropertyValueFactory<>("name"));
       TableColumn price = new TableColumn<>("price");
       price.setCellValueFactory(new PropertyValueFactory<>("price"));
       // adding all at one by getting details from product class and added in product table

       ObservableList<Product> products = Product.getAllProducts();
       productTable = new TableView<>();
       productTable.setItems(products);
       productTable.getColumns().addAll(id,name,price);
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

        ObservableList<Product> products = Product.getProductByName(productByName);
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(supply_chain.width,supply_chain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color : #336678;");
        tablePane.setMinSize(supply_chain.width,supply_chain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    */

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn<>("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
       TableColumn details = new TableColumn<>("Product_Details");
       details.setCellValueFactory(new PropertyValueFactory<>("Product_Details"));
       TableColumn supplier = new TableColumn<>("supplier");
       supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));


        // adding all at one by getting details from product class and added in product table

        ObservableList<Product> products = Product.getAllProducts();
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,details,supplier);
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
        details.setCellValueFactory(new PropertyValueFactory<>("Product_Details"));
        TableColumn supplier = new TableColumn<>("supplier");
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));


        ObservableList<Product> products = Product.getProductByName(productByName);
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price,details,supplier);
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
    public Product getAllProductFromCart (){

        return null;
    }

}
