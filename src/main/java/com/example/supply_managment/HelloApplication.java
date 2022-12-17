package com.example.supply_managment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final int width = 700, height = 600, headerBarsize = 50;
    static Pane bodyPane = new Pane();

    private GridPane headerBar(){
        TextField searchText = new TextField("Search");
        Button searchButton = new Button("Search");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
//        gridPane.setStyle("-fs-background-color : #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchButton,0,0);
        gridPane.add(searchText,1,0);
        return gridPane;
    }
    private GridPane loginPage(){
        Label email = new Label("Email");
        Label password = new Label("Password");
        TextField emailField = new TextField("Enter Your email");
        PasswordField passwordField = new PasswordField();
        Label messageLable = new Label("I Am msg");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailField.getText();
                String password = passwordField.getText();
                messageLable.setText(email + password);
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fs-background-color : #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(email,0,0);
        gridPane.add(emailField,1,0);
        gridPane.add(password,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(messageLable,2 ,1);

        gridPane.add(loginButton ,0,2);
        return gridPane;
    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, headerBarsize+height);

//        bodyPane.setTranslateY(headerBarsize);
        bodyPane.setMinSize(0, headerBarsize);
        bodyPane.getChildren().addAll(headerBar());
        root.getChildren().addAll(loginPage(),bodyPane);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}