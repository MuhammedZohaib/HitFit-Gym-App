package com.example.semesterproject_2022;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Main_Interface_2 implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private Button maxBtn;

    @FXML
    private Button menuBarBtn;

    @FXML
    private AnchorPane navPanel;

    @FXML
    private Button restoreBtn;

    @FXML
    private AnchorPane stageBorderPane;

    private static int Menu_Counter = 0;

    @FXML
    void close(ActionEvent event) {
        new GeneralFunctions().close(closeBtn);
    }

    @FXML
    void max(ActionEvent event) {
        new GeneralFunctions().maxmin(maxBtn);
    }

    @FXML
    void restore(ActionEvent event) {
        new GeneralFunctions().restoring(restoreBtn);
    }

    @FXML
    void menuBar(ActionEvent event) {

        if(Menu_Counter==0) {

            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(0);
            translateTransition.play();

            navPanel.setTranslateX(-186);
            Menu_Counter=1;
        } else if(Menu_Counter==1)
        {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(-186);
            translateTransition.play();

            navPanel.setTranslateX(0);
            Menu_Counter=0;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Menu_Counter==0) {
            navPanel.setTranslateX(-186);
        }
        if(Menu_Counter==1)
        {
            navPanel.setTranslateX(0);
        }
    }
}
