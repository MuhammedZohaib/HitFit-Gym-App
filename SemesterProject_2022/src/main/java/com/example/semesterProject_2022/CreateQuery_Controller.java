package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateQuery_Controller {

    @FXML
    private TextArea DescriptionBox;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField HeadingField;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button exitButton;

    @FXML
    void CreateQueryBtn(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

        new GeneralFunctions().close(exitButton);
    }

}
