module com.example.semesterproject_2022 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.semesterproject_2022 to javafx.fxml;
    exports com.example.semesterproject_2022;
}