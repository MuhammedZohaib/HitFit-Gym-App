package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingScreen_Controller implements Initializable {
    public Stage stage = new Stage();
    public Scene scene;
    @FXML
    private AnchorPane loadingStage;
    @FXML
    public Text LoadingText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // anonymous object
        new LoadingScreen().start();
    }

    // creating a loading screen class so thread can be extended and object can be created to access the start method of Thread
    class LoadingScreen extends Thread
    {
        public void run()
        {
            try {
                DatabaseFunctions.makeConnection();
                Thread.sleep(10000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        try {
                                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginSignUp.fxml"));
                                scene = new Scene(fxmlLoader.load());
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();
                                loadingStage.getScene().getWindow().hide();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}