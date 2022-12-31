package com.example.semesterProject_2022;

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
    private static int Nan_counter=0;
    Rectangle2D dim = Screen.getPrimary().getVisualBounds();


    public  void switchScene(Event e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        obj.stage.show();
    }
    public void close(Button b){
        /*---DB-CLOSE FUNCTION (TO BE ADDED)----*/
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
      //  ResizeHelper.addResizeListener(obj.stage,1280,800,maxWidth,maxHeight);
        obj.stage.show();
    }
    /*-----a function for restoring the window------- */

    public void restoring(Button b)
    {
        obj.stage = (Stage) b.getScene().getWindow();
        obj.stage.setIconified(true);
    }
    public void maxmin(Button b)
    {
        double width= obj.stage.getWidth();
        double height= obj.stage.getHeight();
        if(Nan_counter==0 || height< dim.getHeight() && width<dim.getWidth() ) {

            obj.stage = (Stage) b.getScene().getWindow();
            obj.stage.setHeight(dim.getHeight());
            obj.stage.setWidth(dim.getWidth());
            obj.stage.centerOnScreen();
            Nan_counter=1;
        }
        else if(true)
        {
            obj.stage = (Stage) b.getScene().getWindow();
            obj.stage.setHeight(600);
            obj.stage.setWidth(1024);
            obj.stage.centerOnScreen();
            Nan_counter=0;
        }
    }
}
