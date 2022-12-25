package com.example.semesterproject_2022;

import com.ResizeHelper.ResizeHelper;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralFunctions {
    LoadingScreen_Controller obj = new LoadingScreen_Controller();


    public  void switchScene(Event e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        obj.stage.show();
    }
    public void close(Button b){
        obj.stage = (Stage) b.getScene().getWindow();
        obj.stage.close();
    }

    /*----Overloaded SwitchScene Method because of Resize Helper Class-----*/
    public void switchScene(Event e, String fxml, double maxWidth, double maxHeight) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        /*---------Using the Undecorated Stage we can't resize through native functionalities so here is the function to resize and drag the undecorated stage----------------*/
        ResizeHelper.addResizeListener(obj.stage,1280,800,maxWidth,maxHeight);
        obj.stage.show();
    }
}
