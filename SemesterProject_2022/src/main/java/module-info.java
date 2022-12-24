module com.example.semesterproject_2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires com.mailjet.api;
    requires commons.codec;


    opens com.example.semesterproject_2022 to javafx.fxml;
    exports com.example.semesterproject_2022;
}