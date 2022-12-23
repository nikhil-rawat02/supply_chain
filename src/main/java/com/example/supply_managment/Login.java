package com.example.supply_managment;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Login {
    static Button addToCart = new Button("Add to Cart");
    static Button buyNow = new Button("Buy Now");
    static Button openCart = new Button("My Cart");
    static Button removeFromCart = new Button("Remove");
    static Boolean cartIsOpen = false;
    static Button loginButton = new Button("Login");
    static Label updateStatus = new Label(); // to display message if order placed or add to cart
    static String email;
    static TextField emailField = new TextField();
    static PasswordField passwordField = new PasswordField();
    static Label messageLabel = new Label();
    static Pane allProductDetails;
    static Pane addPdDetails = new Pane();
    public static DatabaseConnection databaseConnection = new DatabaseConnection();

    static void updateHeader(){
        // if user has admin right add new function to open window to add product in db
        Image addPd = new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\addPd.png"); // update new input stream
        ImageView imageViewaddPd = new ImageView(addPd);
        Image pdList = new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\product_list.png");
        ImageView imageViewPdList = new ImageView(pdList);
        Image logout = new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\logout_final.png");
        ImageView imageViewLogout = new ImageView(logout);

        if(databaseConnection.getUserAccessCode(email)){
            // give access to add products and view all products
            supply_chain.gridPaneHeader.add(imageViewaddPd,2,0);
            supply_chain.gridPaneHeader.add(imageViewPdList,3,0);
            imageViewaddPd.setTranslateX(150);
            imageViewPdList.setTranslateX(200);
            imageViewaddPd.setOnMouseClicked(mouseEvent -> {
                supply_chain.bodyPane.getChildren().clear();
                AddProductDetails addProductDetails = new AddProductDetails();
                addPdDetails = addProductDetails.addProduct();
                supply_chain.bodyPane.getChildren().add(addPdDetails);
                BackgroundFill background_fill = new BackgroundFill(Color.CORNSILK,
                        CornerRadii.EMPTY, Insets.EMPTY);
                supply_chain.root.setBackground(new Background(background_fill));
            });
            imageViewPdList.setOnMouseClicked(mouseEvent -> {
                supply_chain.bodyPane.getChildren().clear();
                supply_chain.bodyPane.getChildren().add(allProductDetails);
            });
        }
        imageViewLogout.setOnMouseClicked(mouseEvent -> {
            loginButton.setDisable(false);
            BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\home_main.png",supply_chain.width,supply_chain.height + supply_chain.headerBarsize,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
            supply_chain.root.setBackground(new Background(myBg));
            supply_chain.gridPaneHeader.getChildren().remove(imageViewLogout);
            supply_chain.gridPaneHeader.getChildren().remove(imageViewaddPd);
            supply_chain.gridPaneHeader.getChildren().remove(imageViewPdList);
            supply_chain.gridPaneHeader.add(supply_chain.loginImage,6,0);
            supply_chain.gridPaneHeader.add(supply_chain.preSignUp,8,0);
            supply_chain.gridPaneHeader.add(supply_chain.signup,9,0);
            supply_chain.bodyPane.getChildren().clear();
            supply_chain.bodyPane.getChildren().add(supply_chain.restoreHome);
//            supply_chain.gridPaneFooter.add(supply_chain.FooterInfo, 0,0);
//            supply_chain.gridPaneFooter.add(supply_chain.FooterAskLogin,0,1);
//            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo1,36,0);
//            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo2,36,1);
//            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo3,37,1);
            supply_chain.customerCareInfo1.setTranslateX(269);
            supply_chain.customerCareInfo2.setTranslateX(270);
            supply_chain.customerCareInfo3.setTranslateX(270);
            supply_chain.gridPaneFooter.add(supply_chain.FooterInfo, 0,0);
            supply_chain.gridPaneFooter.add(supply_chain.FooterAskLogin, 0,1);
            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo1,2,0);
            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo2,3,0);
            supply_chain.gridPaneFooter.add(supply_chain.customerCareInfo3,3,1);

            supply_chain.gridPaneFooter.getChildren().remove(addToCart);
            supply_chain.gridPaneFooter.getChildren().remove(buyNow);
            supply_chain.gridPaneFooter.getChildren().remove(openCart);
            supply_chain.gridPaneFooter.getChildren().remove(updateStatus);

        });

        imageViewaddPd.setFitWidth(supply_chain.headerBarsize);
        imageViewaddPd.setFitWidth(supply_chain.headerBarsize);
        imageViewaddPd.setPreserveRatio(true);
        imageViewPdList.setFitWidth(supply_chain.headerBarsize);
        imageViewPdList.setFitHeight(supply_chain.headerBarsize);
        imageViewPdList.setPreserveRatio(true);
        imageViewLogout.setFitHeight(supply_chain.headerBarsize);
        imageViewLogout.setFitWidth(supply_chain.headerBarsize);
        imageViewLogout.setPreserveRatio(true);
        imageViewLogout.setTranslateX(491);

        supply_chain.gridPaneHeader.getChildren().remove(supply_chain.loginImage);
        supply_chain.gridPaneHeader.getChildren().remove(supply_chain.preSignUp);
        supply_chain.gridPaneHeader.getChildren().remove(supply_chain.signup);
        supply_chain.gridPaneHeader.add(imageViewLogout,7,0);
    }
    static void updateFooter(){
        Order order = new Order();
        ProductDetails productDetails = new ProductDetails();
        buyNow.setOnAction(actionEvent -> {
            Product selected = productDetails.getSelectedProduct();

            if (cartIsOpen){ // place all the order in table. create DB for cart to keep track of on cart items
                Product cartProduct = productDetails.getAllProductFromCart();// get all the product from cart;
                order.placeAllOrderFromCart(email,cartProduct);
                updateStatus.setText("Order Placed");
                updateStatus.setTextFill(Color.BLACK);
            }
            else if(selected == null){
                updateStatus.setText("Select item from table");
                updateStatus.setTextFill(Color.RED);
            }
            else if(order.placeOrder(email,selected)){

                updateStatus.setText("Order placed");
                updateStatus.setTextFill(Color.BLACK);
            }else{
                updateStatus.setText("Order Failed");
                updateStatus.setTextFill(Color.RED);
            }
        });
        openCart.setOnAction(actionEvent -> {
            supply_chain.gridPaneFooter.getChildren().remove(addToCart);
            supply_chain.gridPaneFooter.add(removeFromCart,0,0);
            removeFromCart.setTranslateX(300);
            removeFromCart.setTranslateY(10);

            cartIsOpen = true;
            supply_chain.bodyPane.getChildren().clear();
            Cart newCart = new Cart();
            supply_chain.bodyPane.getChildren().add(newCart.getOrderProduct(email));
        });

        supply_chain.gridPaneFooter.getChildren().remove(supply_chain.FooterInfo);
        supply_chain.gridPaneFooter.getChildren().remove(supply_chain.FooterAskLogin);
        supply_chain.gridPaneFooter.getChildren().remove(supply_chain.customerCareInfo1);
        supply_chain.gridPaneFooter.getChildren().remove(supply_chain.customerCareInfo2);
        supply_chain.gridPaneFooter.getChildren().remove(supply_chain.customerCareInfo3);
        supply_chain.gridPaneFooter.add(addToCart,0,0);
        supply_chain.gridPaneFooter.add(buyNow,1,0);
        supply_chain.gridPaneFooter.add(openCart,2,0);
        supply_chain.gridPaneFooter.add(updateStatus,3,0);
        openCart.setTranslateY(10);
        addToCart.setTranslateX(300);
        addToCart.setTranslateY(10);
        buyNow.setTranslateX(400);
        buyNow.setTranslateY(10);
        updateStatus.setTranslateX(400);
        updateStatus.setTranslateY(10);
    }
    public static Pane loginPage() throws FileNotFoundException {
        Label email = new Label("Email");
        Label passwordLabel = new Label("Password");
        Image lock = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\login_1.png"));
        ImageView imageViewLock = new ImageView(lock);

        emailField.setPromptText("username");
        passwordField.setPromptText("password");
        loginButton.setOnAction(actionEvent -> {
            Login getLogin = new Login();
            getLogin.getLoggedIn();
        });
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                Login getLogin = new Login();
                getLogin.getLoggedIn();
            }
        });

        email.setFont(Font.font("MingLiU", FontWeight.BOLD, FontPosture.REGULAR,14));
        passwordLabel.setFont(Font.font("MingLiU", FontWeight.BOLD, FontPosture.REGULAR,14));
        imageViewLock.setFitHeight(100);
        imageViewLock.setFitWidth(100);
        imageViewLock.setPreserveRatio(true);
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(supply_chain.bodyPane.getMinWidth(), supply_chain.bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fs-background-color : #C0C0C0");
        GridPane.setColumnSpan(imageViewLock,2);
        gridPane.add(imageViewLock,13,16);
        gridPane.add(email,13,18);
        gridPane.add(emailField,14,18);
        gridPane.add(passwordLabel,13,19);
        gridPane.add(passwordField,14,19);
        gridPane.add(messageLabel,14,20);
        gridPane.add(loginButton ,13,20);
        return gridPane;
    }
     void getLoggedIn(){
        email = emailField.getText();
        String password = passwordField.getText();
        addtional_function addtional_functions = new addtional_function();
        CustomerDetails customerDetails = new CustomerDetails();
        if(!addtional_functions.validateEmail(email) || customerDetails.checkEmailInDB(email)){
            messageLabel.setText("Enter valid email id!");
            messageLabel.setTextFill(Color.RED);
        }else if (customerDetails.customerLogin(email,addtional_functions.updateInDB(password))){
            messageLabel.setText("Login Successful");
            messageLabel.setTextFill(Color.BLACK);
            loginButton.setDisable(true);
            //update head Pane (header Bar)
            updateHeader();
            //set body Pane
            supply_chain.bodyPane.getChildren().clear();
            ProductDetails productDetails = new ProductDetails();
            allProductDetails = productDetails.getAllProducts();
            supply_chain.bodyPane.getChildren().add(allProductDetails);
            emailField.clear();
            passwordField.clear();
            messageLabel.setText("");
            //update foot Pane
            updateFooter();

        }else if(customerDetails.checkEmailInDB(email)){
            messageLabel.setText("User not found, enter correct email-id");
            messageLabel.setTextFill(Color.BLACK);
        }else if(passwordField.getText().isEmpty()){
             messageLabel.setText("Password field cannot be empty, Enter Password");
             messageLabel.setTextFill(Color.RED);
         }else{
             messageLabel.setText("Credential failed,Please enter correct password");
             messageLabel.setTextFill(Color.RED);
         }
    }
    public static void main(String[] args) {

    }
}
