package com.example.semesterproject_2022;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_Interface {
    Stage stage = new Stage();

    LoadingScreen_Controller obj;
    double x=0,y=0;
    @FXML
    private AnchorPane anchorpane_login;
    @FXML
    void dragged(MouseEvent event) {
//        stage = (Stage) anchorpane_login.getScene().getWindow();
//        stage.setX(event.getScreenX()-x);
//        stage.setY(event.getScreenY()-y);
    }

    @FXML
    void pressed(MouseEvent event) {
//        x=event.getSceneX();
//        y=event.getSceneY();
    }

}
