package com.example.semesterProject_2022;

import backend_functions.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn_Form_Controller {

    @FXML
    private TextField EmailField;

    @FXML
    private Button LogInButton;

    @FXML
    private PasswordField PassField;

    @FXML
    private AnchorPane anchorpane_login;
    @FXML
    private Button exit;
    @FXML
    private Label epValidation;
    @FXML
    private Label passwordValidation;

    /*---------Local Variable------------*/
    private String email;
    private String password;
    private double x = 0;
    private double y = 0;
    /*------*/
    /*---------Loading Screen object----------*/
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    /*For Resolution relative to every screen we will get the dimensions of the screen*/
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    String errorStyle = "-fx-border-color: #ff0000; -fx-border-width: 3px; -fx-border-radius:12px";
    String resetStyle = "-fx-border-color: transparent; -fx-border-width: 3px; -fx-border-radius:12px";

    @FXML
    void loginBtn(MouseEvent e) throws IOException {


        Login newLogin = new Login();

        email = EmailField.getText();
        password = PassField.getText();
        newLogin.setEmailUsername(email);
        newLogin.setPassword(password);



        if (email.isEmpty() || email.isBlank()) {
            epValidation.setText("! Email cannot be empty");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }

        else if (newLogin.checkUsernameEmail("customer")) {
            epValidation.setText("! Invalid email or username");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }

        if (password.isEmpty() || password.isBlank()) {
            passwordValidation.setText("! Password cannot be empty");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (!newLogin.userLoggedInStatus()) {
            passwordValidation.setText("! Password Incorrect");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (!newLogin.checkUsernameEmail("customer") && newLogin.userLoggedInStatus() && epValidation.getText().equals("") && passwordValidation.getText().equals("")) {
            new GeneralFunctions().switchScene(e, "Customer_Dashboard.fxml", screenBounds.getWidth(), screenBounds.getHeight());

        }


    }
    public void adminLogin(ActionEvent e) throws IOException {
        email = EmailField.getText();
        password = PassField.getText();

        if (email.isEmpty() || email.isBlank()) {
            epValidation.setText("! Email cannot be empty");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }
        else if (!email.equals("AyaanAli@9921")) {
            epValidation.setText("! Invalid email or username");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }

        if (password.isEmpty() || password.isBlank()) {
            passwordValidation.setText("! Password cannot be empty");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (!password.equals("L3tM31n_121")) {
            passwordValidation.setText("! Password Incorrect");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (email.equals("AyaanAli@9921") && password.equals("L3tM31n_121") && epValidation.getText().equals("") && passwordValidation.getText().equals("")) {
            new GeneralFunctions().switchScene(e, "DashBoard.fxml", screenBounds.getWidth(), screenBounds.getHeight());

        }
    }
    public void clear(){
        epValidation.setText("");
        EmailField.setStyle(resetStyle);
        passwordValidation.setText("");
        PassField.setStyle(resetStyle);
    }

    @FXML
    void dragged(MouseEvent event) {
        obj.stage = (Stage) anchorpane_login.getScene().getWindow();
        obj.stage.setX(event.getScreenX() - x);
        obj.stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


    public void close() {
        new GeneralFunctions().close(exit);
    }
    @FXML
    void GoBackLogIn(ActionEvent e) throws IOException {
        new GeneralFunctions().switchScene(e, "LoginSignUp.fxml");
    }
}
