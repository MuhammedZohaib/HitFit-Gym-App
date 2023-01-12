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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
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
    class LoadingScreen extends Thread {
        public void run() {
            try {
                DatabaseFunctions.makeConnection();
                Thread.sleep(10000);
                File cookie = new File("credentials.properties");

                if (cookie.exists()) {
                    Properties prop = new Properties();
                    try (InputStream input = new FileInputStream("credentials.properties")) {
                        prop.load(input);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String username = prop.getProperty("Username");
                    String password = prop.getProperty("password");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new LogIn_Form_Controller().login(username, password);
                                loadingStage.getScene().getWindow().hide();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginSignUp.fxml"));
                            try {
                                scene = new Scene(fxmlLoader.load());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.show();
                            loadingStage.getScene().getWindow().hide();
                        }
                    });

                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    }
