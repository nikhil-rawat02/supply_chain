package com.example.supply_managment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
public class SignUp {
    public static Pane signUpPage(){
        Label signup = new Label("Sign Up");
        Label text = new Label("It's quick and easy.");
        Label nameLabel = new Label("Name : ");
        Label lastNameLabel = new Label("Surname : ");
        Label emailLabel = new Label("Email : ");
        Label mobileLabel = new Label("Contact No. : ");
        Label addressLabel = new Label("Residence : ");
        Label passwordLabel = new Label("Password : ");
        Label status = new Label();

        TextField name = new TextField();
        TextField lastName = new TextField();
        TextField email = new TextField();
        TextField mobile = new TextField();
        TextField address = new TextField();
        PasswordField password = new PasswordField();
        Button submit = new Button("Submit");
        Button reset = new Button("Rest All");

        name.setPromptText("First name");
        lastName.setPromptText("Surname");
        email.setPromptText("valid Email id");
        mobile.setPromptText("Valid mobile number");
        address.setPromptText("Residence");
        password.setPromptText("password");

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DatabaseConnection databaseConnection = new DatabaseConnection();
                CustomerDetails customerDetails = new CustomerDetails();
                addtional_function addfnc = new addtional_function();
                String query = String.format("INSERT INTO CUSTOMER (email,password,first_name,last_name,address,mobile) values ('%s','%s','%s','%s','%s','%s')",email.getText(),addfnc.updateInDB(password.getText()),name.getText(),lastName.getText(),address.getText(),mobile.getText());
                int rowCount = 0;
                // check email exist in db or not
                if (name.getText().trim().isEmpty() || email.getText().trim().isEmpty()
                        || mobile.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
                    status.setText("above fields cannot be empty !");
                    status.setTextFill(Color.RED);
                } else if (!customerDetails.checkEmailInDB(email.getText())) {
                    status.setText("this email-Id is already registered");
                } else {
                    try {
                        rowCount = databaseConnection.executeUpdateQuery(query);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (rowCount != 0) status.setText("new Account created now try login");
                }
            }
        });
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name.clear();
                lastName.clear();
                email.clear();
                mobile.clear();
                address.clear();
                password.clear();
                status.setText("");
            }
        });
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(supply_chain.bodyPane.getMinWidth(), supply_chain.bodyPane.getMinHeight());
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);

        signup.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC, 16));
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));
        lastNameLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));
        addressLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));
        mobileLabel.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,11));


        GridPane.setColumnSpan(signup,2);
        GridPane.setColumnSpan(text,2);
        GridPane.setColumnSpan(status,2);
        submit.setTranslateY(10);
        reset.setTranslateY(10);
        reset.setTranslateX(25);

        gridPane.add(signup, 0, 0);
        gridPane.add(text,0, 1);
        gridPane.add(nameLabel,0,2);
        gridPane.add(lastNameLabel,0,3);
        gridPane.add(emailLabel,0,4);
        gridPane.add(mobileLabel,0,5);
        gridPane.add(addressLabel,0,6);
        gridPane.add(passwordLabel,0,7);
        gridPane.add(name, 1,2);
        gridPane.add(lastName,1,3);
        gridPane.add(email,1,4);
        gridPane.add(mobile,1,5);
        gridPane.add(address,1,6);
        gridPane.add(password,1,7);
        gridPane.add(submit, 0 ,8);
        gridPane.add(reset,1,8);
        gridPane.add(status, 0, 9);

        return gridPane;
    }
}
