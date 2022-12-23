package com.example.supply_managment;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Cart {

    public static TableView<Product> productTableView;
    ObservableList <Product> products;
    public Pane getOrderProduct(String email){
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

        products = Order.getOrder(email);
        productTableView = new TableView<>();
        productTableView.setItems(products);
        productTableView.getColumns().addAll(name,price,details,supplier);
        productTableView.setMinSize(supply_chain.width,supply_chain.height);
        productTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color : #336678;");
        tablePane.setMinSize(supply_chain.width,supply_chain.height);
        tablePane.getChildren().add(productTableView);
        return tablePane;
    }
}
