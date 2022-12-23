package com.example.supply_managment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class supply_chain extends Application {
    static Pane basePane;
    public static final int width = 928, height = 500, headerBarsize = 50;
    static Pane root;
    static Pane bodyPane = new Pane();
    static Pane restoreHome = new Pane();
    public static GridPane gridPaneHeader = new GridPane();
    public static GridPane gridPaneFooter = new GridPane();
    public static Label FooterInfo;
    public static Label FooterAskLogin;
    public static Label customerCareInfo1;
    public static Label customerCareInfo2;
    public static Label customerCareInfo3;
    public static TextField searchText;
    public static Button searchButton;
    public static Image loginImg;
    public static ImageView loginImage;
    public static  Label preSignUp;
    public static Hyperlink signup;
    ProductDetails productDetails= new ProductDetails();
    public GridPane headerBar() throws FileNotFoundException {
        searchText = new TextField();
        searchButton= new Button("Search");
        preSignUp= new Label("Not a member? ");
        signup = new Hyperlink("Sign up");
        loginImg = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\loginbutton.jpg"));
        loginImage = new ImageView(loginImg);
        Image smallLogo = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\header_logo.png"));
        ImageView imageViewSmallLogo = new ImageView(smallLogo);

        searchText.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                String productName = searchText.getText();
                if(searchText.getText().isEmpty()){
                    searchText.setPromptText("Enter Details");
                }else{
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getProductsByName(productName));
                }
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                if(searchText.getText().isEmpty()){
                    searchText.setPromptText("Enter Details");
                }else{
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getProductsByName(productName));
                }
            }
        });
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\sign_up1.png",width,height + headerBarsize,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
                root.setBackground(new Background(myBg));

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(SignUp.signUpPage());
            }
        });
        loginImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\home_main_3.jpg",width,height + headerBarsize,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
                root.setBackground(new Background(myBg));
                bodyPane.getChildren().clear();
                try{
                    bodyPane.getChildren().add(Login.loginPage());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        searchText.setTranslateX(280);
        searchButton.setTranslateX(300);
        loginImage.setTranslateX(348);
        preSignUp.setTranslateX(349);
        signup.setTranslateX(350);
        imageViewSmallLogo.setFitHeight(headerBarsize);
        imageViewSmallLogo.setFitWidth(headerBarsize);
        loginImage.setFitHeight(headerBarsize*2);
        loginImage.setFitWidth(120);

        loginImage.setPreserveRatio(true);
        imageViewSmallLogo.setPreserveRatio(true);

        preSignUp.setFont(Font.font("MingLiU", FontWeight.BOLD, FontPosture.REGULAR,14));
        signup.setFont(Font.font("MingLiU", FontWeight.BOLD, FontPosture.REGULAR,14));

        GridPane headerPane = new GridPane();
        headerPane.setMinSize(width, headerBarsize);

        headerPane.setVgap(5);
        headerPane.setHgap(5);
        headerPane.setStyle("-fx-background-color : #336699;");

        headerPane.add(searchText,4,0);
        headerPane.add(searchButton,5,0);
        headerPane.add(preSignUp,8,0);
        headerPane.add(signup,9,0);
        headerPane.add(loginImage,6,0);
        headerPane.add(imageViewSmallLogo,0,0);

        return headerPane;
    }
    private GridPane footerBar(){
        FooterInfo = new Label();
        FooterAskLogin = new Label();
        customerCareInfo1 = new Label();
        customerCareInfo2 = new Label();
        customerCareInfo3 = new Label();

        FooterInfo.setText("This website is prototype of E-commercial websites with very basic functionality");
        FooterAskLogin.setText("Check and buy more products by login");
        customerCareInfo1.setText("contact us:");
        customerCareInfo2.setText("(+91) 99999-99999");
        customerCareInfo3.setText("myshopping@support.com");

        customerCareInfo1.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,14));
        GridPane footerPane = new GridPane();
        footerPane.setTranslateY(height+headerBarsize);
        footerPane.setMinSize(width, headerBarsize);
        footerPane.setStyle("-fx-background-color : #808080;");

        footerPane.setVgap(5);
        footerPane.setHgap(5);
        customerCareInfo1.setTranslateX(269);
        customerCareInfo2.setTranslateX(270);
        customerCareInfo3.setTranslateX(270);
        footerPane.add(FooterInfo, 0,0);
        footerPane.add(FooterAskLogin, 0,1);
        footerPane.add(customerCareInfo1,2,0);
        footerPane.add(customerCareInfo2,3,0);
        footerPane.add(customerCareInfo3,3,1);

        return footerPane;
    }
    private GridPane homePage(){
        Label homeMessage1 = new Label("Welcome to My Shopping! ");
        Label homeMessage2 = new Label("One of the best upcoming commercial website ");
        Label homeMessage3 = new Label("So what are you waiting for?");
        Label homeMessage4 = new Label("grab best deals this season from My Shopping");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        homeMessage1.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,27));
        homeMessage2.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,27));
        homeMessage3.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,27));
        homeMessage4.setFont(Font.font("sin script", FontWeight.BOLD, FontPosture.ITALIC,27));

        gridPane.add(homeMessage1,0,1);
        gridPane.add(homeMessage2,0,2);
        gridPane.add(homeMessage3,0,3);
        gridPane.add(homeMessage4,0,4);


        return gridPane;
    }
    private Pane createContent() throws FileNotFoundException {
        root = new Pane();
        BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\home_main.png",width,height + headerBarsize,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBg));

        // basic home page main head and foot pane enable
        root.setPrefSize(width, headerBarsize*2 +height);

        gridPaneHeader.setMinSize(width, headerBarsize);
        gridPaneHeader = headerBar();
        gridPaneHeader.setStyle("-fx-background-color : #336699;");

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBarsize);
        restoreHome = homePage();
        bodyPane.getChildren().add(restoreHome);

        gridPaneFooter.setMinSize(width, headerBarsize);
        gridPaneFooter = footerBar();

        root.getChildren().addAll(gridPaneHeader,bodyPane,gridPaneFooter);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        basePane = new Pane(createContent());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(width, height + 2*headerBarsize);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(basePane);

        Scene scene = new Scene(basePane);
        stage.setTitle("My Shopping!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}