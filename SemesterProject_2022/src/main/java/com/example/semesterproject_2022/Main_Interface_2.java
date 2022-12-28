package com.example.semesterproject_2022;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class Main_Interface_2 implements Initializable {
    @FXML
    private StackPane stckPane;
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
    public AnchorPane pane;
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
    void menuBar(ActionEvent event)  {


        if(Menu_Counter==0) {

            // for nav pane
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(0);
            translateTransition.play();

            // for stack pane
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(400));
            translateTransition1.setNode(stckPane);
            translateTransition1.setToX(-50);
            translateTransition1.play();

            stckPane.setTranslateX(-200);
            navPanel.setTranslateX(-186);
            Menu_Counter=1;
        } else if(Menu_Counter==1)
        {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(-186);
            translateTransition.play();

            // for stack pane
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(400));
            translateTransition1.setNode(stckPane);
            translateTransition1.setToX(-186);
            translateTransition1.play();

            stckPane.setTranslateX(0);
            navPanel.setTranslateX(0);
            Menu_Counter=0;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(DasboardPanel.class.getResource("DasboardPanel.fxml"));
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stckPane.getChildren().addAll(pane);

        /*-- added a library called "aniamtefx" ver 1.2.0 from Maven library ( -Saad S. )--*/
        new animatefx.animation.FadeIn(stckPane).play();

        /*---nav pane animation code starts here--*/
        if(Menu_Counter==0) {
            navPanel.setTranslateX(-186);
            stckPane.setTranslateX(-186);
        }
        if(Menu_Counter==1)
        {
            navPanel.setTranslateX(0);
            stckPane.setTranslateX(0);
        }

    }
}
