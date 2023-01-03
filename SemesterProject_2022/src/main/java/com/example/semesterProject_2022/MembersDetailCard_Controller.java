package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MembersDetailCard_Controller {

    MembersPanel_Controller membersPanel_controller = new MembersPanel_Controller();
    private double x=0;
    private double y=0;
    @FXML
    private Text address;

    @FXML
    private Button closeButton;

    @FXML
    private Text email;

    @FXML
    private Text fullName;

    @FXML
    private Text packageprice;

    @FXML
    private Text packagetype;

    @FXML
    private Text username;

    @FXML
    private Text weight;
    @FXML
    private AnchorPane Main;
    @FXML
    void closebtn(ActionEvent event) {
        new GeneralFunctions().close(closeButton);
    }
    public void dragWindow(MouseEvent e)
    {
        membersPanel_controller.membercardstage = (Stage) Main.getScene().getWindow();

        membersPanel_controller.membercardstage.setX(e.getScreenX()-x);

        membersPanel_controller.membercardstage.setY(e.getScreenY()-y);
    }
    @FXML
    public void pressedWindow(MouseEvent e)
    {
        x = e.getSceneX();
        y= e.getSceneY();
    }
}
