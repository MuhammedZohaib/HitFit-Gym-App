package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogIn_Form_Controller {

    @FXML
    private TextField EmailField;

    @FXML
    private Button LogInbtn,exit;

    @FXML
    private PasswordField PassField;

    @FXML
    private AnchorPane anchorpane_login;

    @FXML
    private Text epValidation;

    /*---------Local Variable------------*/
    private double x = 0;
    private double y = 0;
    /*------*/
    /*---------Loading Screen object----------*/
    LoadingScreen_Controller obj = new LoadingScreen_Controller();

    @FXML
    void dragged(MouseEvent event) {
        obj.stage = (Stage) anchorpane_login.getScene().getWindow();
        obj.stage.setX(event.getScreenX()-x);
        obj.stage.setY(event.getScreenY()-y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }

    @FXML
    public void close(ActionEvent e){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
