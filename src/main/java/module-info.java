module com.example.supply_managment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supply_managment to javafx.fxml;
    exports com.example.supply_managment;
}