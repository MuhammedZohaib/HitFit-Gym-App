package com.example.semesterProject_2022;

import com.ResizeHelper.FXResizeHelper;
import com.ResizeHelper.ResizeHelper;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GeneralFunctions {
    public Stage stage;
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
    //Overloaded switchScene for max screens to avoid disruptions of Stages
    public void switchScene(String fxml) throws IOException {
        Stage stage = new Stage() ;
        Scene scene;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void switchSceneModality(String fxml) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void close(Button b){
        /*---DB-CLOSE FUNCTION (TO BE ADDED)----*/
        obj.stage = (Stage) b.getScene().getWindow();
        obj.stage.close();
    }
    public void modalityClose(Button b){
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }
    /*----Overloaded SwitchScene Method because of Resize Helper Class-----*/
    public void switchSceneFXHelper(Event e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);

        /*---------Using the Undecorated Stage we can't resize through native functionalities so here is the function to resize and drag the undecorated stage----------------*/
        FXResizeHelper fxResizeHelper = new FXResizeHelper(obj.stage, 10, 10);
        obj.stage.show();
        obj.stage.centerOnScreen();
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
