package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;





public class LoginSignUp_Controller {

    // local variables not from FXML (**declare non-fxml fields here**)

    /*X and Y coordinates for dragging window (Start) */
    private double x = 0;
    private double y = 0;
    /*--------------------------------------- (End)*/

    // ------------------

    @FXML
    private AnchorPane pane;
    @FXML
    private Button exit;
    LoadingScreen_Controller obj = new LoadingScreen_Controller();

    public void switchToSignUp(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        obj.stage.show();
    }

    /*CLOSE BUTTON LOGIC STARTS HERE ----*/
    @FXML
    public void close(ActionEvent e){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    /*CLOSE BUTTON LOGIC ENDS HERE ----*/


    /*DRAGGING WINDOW LOGIC STARTS HERE -----------*/
    @FXML
    void dragged(MouseEvent event) {
        obj.stage = (Stage) pane.getScene().getWindow();
        obj.stage.setX(event.getScreenX()-x);
        obj.stage.setY(event.getScreenY()-y);
    }
    @FXML
    void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }
    /*DRAGGING WINDOW LOGIC ENDS HERE -----------*/

}
