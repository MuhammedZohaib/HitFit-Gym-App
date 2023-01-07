package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class AddEmployeeController {

    @FXML
    private AnchorPane AccountInfopane;

    @FXML
    private ToggleGroup Group1;

    @FXML
    private TextField Usernamefield;

    @FXML
    private Button addmember;

    @FXML
    private MenuButton designationmenubutton;

    @FXML
    private TextField emailfield;

    @FXML
    private RadioButton femaleradiobutton;

    @FXML
    private TextField firstnamefield;

    @FXML
    private DatePicker joindatefield;

    @FXML
    private TextField lastnamefield;

    @FXML
    private RadioButton maleradiobutton;

    @FXML
    private MenuItem menuitem1;

    @FXML
    private MenuItem menuitem2;

    @FXML
    private MenuItem menuitem3;

    @FXML
    private MenuItem menuitem4;

    @FXML
    private MenuItem menuitem5;

    @FXML
    private Button next;

    @FXML
    private TextField nicfield;

    @FXML
    private TextField passwordfield;

    @FXML
    private AnchorPane personalInfo;

    @FXML
    private TextField phonenofield;

    @FXML
    private TextField salaryfield;

    @FXML
    private StackPane stackpane;

    @FXML
    void addmemberactionbutton(ActionEvent event) {

    }

    @FXML
    void menuitem1actionbutton(ActionEvent event) {

    }

    @FXML
    void menuitem2actionbutton(ActionEvent event) {

    }

    @FXML
    void menuitem3actionbutton(ActionEvent event) {

    }

    @FXML
    void menuitem4actionbutton(ActionEvent event) {

    }

    @FXML
    void menuitem5actionbutton(ActionEvent event) {

    }

    @FXML
    void nextactionbutton(ActionEvent event) {


        // this is to switch the anchor pane in the stack pane (basically,
        // when the user presses the next button this should hide the personal info pane,
        // and display the account info pane)

       stackpane.getChildren().get(1).setVisible(false);
       stackpane.getChildren().get(0).setVisible(true);
    }

}
