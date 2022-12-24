package com.example.semesterproject_2022;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main_Interface {
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    Rectangle2D screenRes = Screen.getPrimary().getVisualBounds();



    @FXML
    private Button exit;
    @FXML
    private Button minimize;
    @FXML
    private ImageView profile;

    /*----Windows Border Icons Functionality------------*/

    @FXML
    void close() {
        new GeneralFunctions().close(exit);
    }


    @FXML
    void maximizeBtn() {

        
    }

    @FXML
    void minimizeBtn() {
        obj.stage=(Stage) minimize.getScene().getWindow();
        obj.stage.setIconified(true);
    }

}
