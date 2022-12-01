module com.example.hitfit_v1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hitfit_v1 to javafx.fxml;
    exports com.example.hitfit_v1;
}