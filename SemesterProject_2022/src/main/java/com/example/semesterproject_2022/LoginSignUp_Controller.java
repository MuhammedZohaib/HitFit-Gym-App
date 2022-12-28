package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        new GeneralFunctions().switchScene(e,"SignUp.fxml");
    }
    public void switchToLogin(ActionEvent e) throws IOException {
        new  GeneralFunctions().switchScene(e,"LogIn_Form.fxml");
    }

    @FXML
    public void close(){
        new GeneralFunctions().close(exit);
    }


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
