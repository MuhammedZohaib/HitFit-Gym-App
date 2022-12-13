package com.example.semesterproject_2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< Updated upstream
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoadingScreen.fxml"));
=======
<<<<<<< HEAD
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp_Done_Info.fxml"));
=======
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoadingScreen.fxml"));
>>>>>>> 94aca1477ef3dabfeece14aab1d8b0a18c704b5a
>>>>>>> Stashed changes
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}