package com.example.supply_managment;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.supply_managment.DatabaseConnection.getStatement;

public class AddProductDetails {
    String productName;
    DatabaseConnection dbConn = new DatabaseConnection();
    public Pane addProduct(){
        Label heading = new Label(" Update Product Details in DataBase ");
        Label product_id = new Label("Product Id ");
        Label quantity = new Label("Quantity");
        Label price = new Label("Price");
        Label message = new Label();
        TextField textFieldProductId = new TextField();
        TextField textFieldQuantity = new TextField();
        TextField textFieldPrice = new TextField();
        Button update = new Button("Update");
        Button reset = new Button("Reset");

        textFieldProductId.setPromptText("Product id");
        textFieldQuantity.setPromptText("Enter Quantity");
        textFieldPrice.setPromptText("enter Price");

        update.setOnAction(actionEvent -> {
            if(textFieldProductId.getText().isEmpty()){
                message.setText("Product Id cannot be empty");
                message.setTextFill(Color.RED);
            }else if (!checkProductId(textFieldProductId.getText())) {
                message.setText("productId not found in database create new product in database");
                message.setTextFill(Color.RED);
            } else if (textFieldQuantity.getText().isEmpty() && textFieldPrice.getText().isEmpty()) {
                message.setText("Both quantity and price cannot be empty at least fill one ");
                message.setTextFill(Color.RED);
            } else if(!textFieldProductId.getText().isEmpty() && !textFieldQuantity.getText().isEmpty() && textFieldPrice.getText().isEmpty()) {
                String query =String.format("UPDATE product SET qantity = qantity + '%d' WHERE productId = '%d'",Integer.parseInt(textFieldQuantity.getText()),Integer.parseInt(textFieldProductId.getText()));

                int rowCount = 0;
                try {
                    rowCount = dbConn.executeUpdateQuery(query);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(rowCount == 0){
                    message.setText("entry not done, check database");
                    message.setTextFill(Color.RED);
                }else {
                    String msg = String.format("Quantity of product Id '%s' has been updated",textFieldProductId.getText());
                    message.setText(msg);
                    message.setTextFill(Color.BLACK);
                }
            }else if(!textFieldProductId.getText().isEmpty() && textFieldQuantity.getText().isEmpty() && !textFieldPrice.getText().isEmpty()){
                String query = String.format("UPDATE product SET price = '%s' WHERE productId = '%d'",Integer.parseInt(textFieldPrice.getText()),   Integer.parseInt(textFieldProductId.getText()));

                int rowCount = 0;
                try{
                    rowCount = dbConn.executeUpdateQuery(query);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(rowCount == 0){
                    message.setText("entry not done, check database");
                    message.setTextFill(Color.RED);
                }else {
                    String msg = String.format("Price of product Id '%s' has been updated",textFieldProductId.getText());message.setText(msg);
                    message.setTextFill(Color.BLACK);
                }
            }
        });
        reset.setOnAction(actionEvent -> {
            textFieldQuantity.clear();
            textFieldPrice.clear();
            textFieldProductId.clear();
            message.setText("");
        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(supply_chain.bodyPane.getMinWidth(), supply_chain.bodyPane.getMinHeight());
        gridPane.setHgap(5);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(heading,0,0);
        GridPane.setColumnSpan(heading,5);
        heading.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,14));
        product_id.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        quantity.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        product_id.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,12));
        GridPane.setColumnSpan(message,2);
        gridPane.add(product_id,0,1);
        gridPane.add(quantity,0,2);
        gridPane.add(price,0,3);
        gridPane.add(textFieldProductId,1,1);
        gridPane.add(textFieldQuantity,1,2);
        gridPane.add(textFieldPrice,1,3);
        gridPane.add(update, 0 ,5);
        gridPane.add(reset, 1 , 5);
        gridPane.add(message,0,6);
        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }
    private boolean checkProductId(String productId){

        String query = String.format("SELECT productId from product where productId = %d",Integer.parseInt(productId));
        try{
            ResultSet rs = dbConn.getQuerryTable(query);
            if(rs != null && rs.next())return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
