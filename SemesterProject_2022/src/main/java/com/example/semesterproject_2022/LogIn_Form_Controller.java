package com.example.semesterproject_2022;

import com.ResizeHelper.ResizeHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogIn_Form_Controller {

    @FXML
    private TextField EmailField;

    @FXML
    private Button LogInButton;

    @FXML
    private PasswordField cPassField;

    @FXML
    private AnchorPane anchorpane_login;
    @FXML
    private Button exit;
    @FXML
    private Text epValidation;

    /*---------Local Variable------------*/
    private double x = 0;
    private double y = 0;
    /*------*/
    /*---------Loading Screen object----------*/
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    /*For Resolution relative to every screen we will get the dimensions of the screen*/
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    @FXML
    void loginBtn(MouseEvent e) throws IOException {

        new GeneralFunctions().switchScene(e,"Main_Dashboard.fxml",screenBounds.getWidth(),screenBounds.getHeight());
    }
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
    public void close(){
        new GeneralFunctions().close(exit);
    }

}
