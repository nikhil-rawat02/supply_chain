package com.example.supply_managment;

import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class supply_chain extends Application {
    /* supply chain class is the main class where whole pane/ window has been divided into three part.
       1. header Pane 2. Body Pane and 3. Footer Pane.
       Header Pane , body pane and footer pane contains all the contents i.e. buttons, images, hyperlink etc. that has been located on respective pane.
     */
    public static final int width = 928, height = 500, headerBarsize = 50;
    static Scene scene;
    static Pane root;
    static Pane bodyPane = new Pane();
    static Pane restoreHome = new Pane();
    static GridPane gridPaneHeader = new GridPane();
    static GridPane gridPaneFooter = new GridPane();
    static Label FooterInfo;
    static Label FooterAskLogin;
    static Label customerCareInfo1;
    static Label customerCareInfo2;
    static Label customerCareInfo3;
    static TextField searchText;
    static Button searchButton;
    static Image loginImg;
    static ImageView loginImage;
    static  Label preSignUp;
    static Hyperlink signup;
    ProductDetails productDetails= new ProductDetails();

    public GridPane headerBar() throws FileNotFoundException {
        /* this is the method that loads the whole header bar
           into header pane this method contains text fields to
           take input from user for search of particular product,
           search button to search for particular product mentioned
           in search textField, image name login is used to redirect
           from home to user login page, HyperLink Signup is also
           used to redirect from home to Signup page.
         */
        searchText = new TextField();
        searchButton= new Button("Search");
        preSignUp= new Label("Not a member?");
        signup = new Hyperlink("Sign up");
        loginImg = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\loginbutton.jpg"));
        loginImage = new ImageView(loginImg);
        Image smallLogo = new Image(new FileInputStream("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\header_logo.png"));
        ImageView imageViewSmallLogo = new ImageView(smallLogo);

        //Dynamic search for products not working need to dedub error
/*        FilteredList<Product> filteredList = new FilteredList<>(ProductDetails.products, b-> true);
        searchText.textProperty().addListener((observableValue, oldValue, newValue) ->{
            filteredList.setPredicate(Product ->{
                if(newValue == null || newValue.isEmpty())return  true;
                String loweCaseFilter = newValue.toLowerCase();
                if(Product.getName().toLowerCase().contains(loweCaseFilter)) return true;
                else if(Product.getProductDetails().toLowerCase().contains(loweCaseFilter))return true;
                else return false;

            });
        });
        SortedList <Product> sortedData = new SortedList<>(ProductDetails.products);
        sortedData.comparatorProperty().bind(ProductDetails.productTable.comparatorProperty());
        ProductDetails.productTable.setItems(sortedData);
*/

        searchText.setOnKeyPressed(keyEvent -> {
            /* search text field has been set on action
               when user press enter content mentioned in
               search text filed will be set to search for
               particular/ related product.
             */
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
            /* search button has been set on action when user click
               on button using mouse content mentioned in search text
               filed will be set to search for particular/ related product.
             */
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
            /* signup Hyperlink has been set on action to set clear previous body pane
               content and load it with signup page.
               sign up page details will be fetched from signUpPage method from SignUp class and
               set it on body pane
             */
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
                try{
                    bodyPane.getChildren().add(Login.loginPage());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        GridPane headerPane = new GridPane();
        /* After initializing Grid pane for header,
           CSS styling of all children's of header
           pane and adding then into header pane
         */
        headerPane.setMinSize(width, headerBarsize);
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
        /* this is the method that loads the whole footer bar
           into footer pane this method contains Labels and to
           give good suggestion for how to use this web and
           contact details
         */
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
        /* this method load the home page as soon as the application
           runs and after user logged out from the session
         */
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
        /* createContent method is used to initialize header bar,
           footer bar and home page and assign from to respective pane
         */
        root = new Pane();
        BackgroundImage myBg = new BackgroundImage(new Image("C:\\Users\\Dpk\\Desktop\\Java\\supply_managment\\src\\images\\home_main.png",width,height + headerBarsize,false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBg));
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
        /* all the application content has been initialized
           by calling create content method and assigned it to
           scroll pane due to necessary of scroll bar to adjust
           window size as needed
         */
        ScrollPane scrollPane = new ScrollPane(createContent());
        scrollPane.setPrefSize(width+2, height + 2*headerBarsize + 2);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

         scene = new Scene(scrollPane);
        stage.setTitle("My Shopping!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}