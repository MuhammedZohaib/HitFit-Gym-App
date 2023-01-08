package com.example.semesterProject_2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model_class.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeesDetailCard_Controller implements Initializable {
    GeneralFunctions generalFunctions = new GeneralFunctions();
    MembersPanel_Controller membersPanel_controller = new MembersPanel_Controller();
    private double x=0;
    private double y=0;
    /* Non FXML fields */
    public static String FullName, Phone, Gender, Emails, Username, Designation, Salary;

    @FXML
    private Text phone;

    @FXML
    private Button closeButton;

    @FXML
    private Text email;

    @FXML
    private Text fullName;

    @FXML
    private Text salary;

    @FXML
    private Text designation;

    @FXML
    private Text username;

    @FXML
    private Text gender;

    @FXML
    private AnchorPane Main;
    @FXML
    void closebtn(ActionEvent event) {
        new GeneralFunctions().close(closeButton);
    }
    public void dragWindow(MouseEvent e)
    {
        generalFunctions.stage = (Stage) Main.getScene().getWindow();

        generalFunctions.stage.setX(e.getScreenX()-x);

        generalFunctions.stage.setY(e.getScreenY()-y);
    }
    @FXML
    public void pressedWindow(MouseEvent e)
    {
        x = e.getSceneX();
        y= e.getSceneY();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName.setText(FullName);
        phone.setText(Phone);
        gender.setText(Gender);
        email.setText(Emails);
        username.setText(Username);
        designation.setText(Designation);
        salary.setText(Salary);
    }
}
