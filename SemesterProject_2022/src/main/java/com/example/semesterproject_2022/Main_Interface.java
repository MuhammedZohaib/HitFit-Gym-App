package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main_Interface {
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    Rectangle2D screenres = Screen.getPrimary().getVisualBounds();

    @FXML
    private Button exit;

    @FXML
    private Button minimize;

    /*----Windows Border Icons Functionality------------*/

    @FXML
    void close(ActionEvent event) {
        obj.stage = (Stage) exit.getScene().getWindow();
        obj.stage.close();
    }


    @FXML
    void maximizebtn(ActionEvent event) {

        
    }

    @FXML
    void minimzebtn(ActionEvent event) {
        obj.stage=(Stage) minimize.getScene().getWindow();
        obj.stage.setIconified(true);
    }

}
