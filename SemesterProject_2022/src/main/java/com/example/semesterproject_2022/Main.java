package com.example.semesterproject_2022;

import com.ResizeHelper.ResizeHelper;
import database.DatabaseFunctions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoadingScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.centerOnScreen();

    }


    public static void main(String[] args) {
        DatabaseFunctions.makeConnection();
        launch();
    }
}