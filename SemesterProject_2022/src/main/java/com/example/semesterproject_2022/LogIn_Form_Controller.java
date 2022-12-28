package com.example.semesterproject_2022;

import backend_functions.Login;
import com.ResizeHelper.ResizeHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

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
    private Text epValidation;

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

    @FXML
    void loginBtn(MouseEvent e) throws IOException {

        new GeneralFunctions().switchScene(e, "DashBoard.fxml", screenBounds.getWidth(), screenBounds.getHeight());

        Login newLogin = new Login();

        email = EmailField.getText();
        password = PassField.getText();

        if (email.equals(" ")) {
            System.out.println("email cannot be empty");
        }

        if (password.equals(" ")) {
            System.out.println("password cannot be empty");
        }

        newLogin.setEmailUsername(email);

        if (newLogin.checkUsernameEmail()) {
            System.out.println("An account does not exist with the entered email or username");
        }

        newLogin.setPassword(password);

        if (!newLogin.userLoggedInStatus()) {
            System.out.println("Wrong Password!");
        }

        if (!email.equals(" ") && !password.equals(" ") && !newLogin.checkUsernameEmail() && newLogin.userLoggedInStatus()) {

            System.out.println("User logged in successfully");

            new GeneralFunctions().switchScene(e, "DashBoard.fxml", screenBounds.getWidth(), screenBounds.getHeight());
        }


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

}
