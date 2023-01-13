package com.example.semesterProject_2022;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class QueryView implements Initializable{
    public static String username, email, heading, description;

    @FXML
    private AnchorPane Main;
    @FXML
    private Button closeButton;

    @FXML
    private Text descriptionTxt;

    @FXML
    private Text emailTxt;

    @FXML
    private Text headingTxt;

    @FXML
    private Text usernameTxt;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            usernameTxt.setText(username);
            emailTxt.setText(email);
            headingTxt.setText(heading);
            descriptionTxt.setText(description);
        }

    public void dragWindow(MouseEvent event) {
    }

    public void pressedWindow(MouseEvent event) {
    }
}


