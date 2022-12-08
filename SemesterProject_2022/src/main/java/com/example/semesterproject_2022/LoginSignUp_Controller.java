package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;





public class LoginSignUp_Controller {
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
    @FXML
    public void close(ActionEvent e){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
