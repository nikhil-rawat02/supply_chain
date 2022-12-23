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
import java.sql.Statement;

import static com.example.supply_managment.DatabaseConnection.getStatement;

public class AddProductDetails {
    String productName;
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
            if(textFieldQuantity.getText().isEmpty() || textFieldPrice.getText().isEmpty() || textFieldProductId.getText().isEmpty()){
                message.setText("Enter above details");
                message.setTextFill(Color.RED);
            }else{
                productName  = textFieldProductId.getText();
                String query =String.format("write query here ",textFieldProductId.getText(),textFieldQuantity.getText(), textFieldPrice.getText());
                Statement statement = getStatement();
                try {
                    statement.executeQuery(query);
                }catch (Exception e){
                    e.printStackTrace();
                }
                message.setText("Details has been updated");
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
        GridPane.setColumnSpan(heading,2);
        heading.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,14));
        product_id.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        quantity.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR,12));
        product_id.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,12));
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
}
