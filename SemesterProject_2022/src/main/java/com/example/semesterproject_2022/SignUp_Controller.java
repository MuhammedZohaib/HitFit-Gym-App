package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp_Controller {

    // local variables not from FXML (**declare non-fxml fields here**)

        /*X and Y coordinates for dragging window*/
        private double x = 0;
        private double y = 0;
        /*--------------------------------------- */

    // ------------------

    @FXML
    private AnchorPane Main;

    @FXML
    private PasswordField cPassword;

    @FXML
    private AnchorPane child;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private HBox hbox;

    @FXML
    private TextField lName;

    @FXML
    private TextField password;

    @FXML
    private TextField uName;

    @FXML
    private Button exit;

    @FXML
    private Text Texts;
    @FXML
    private ImageView loading;

    @FXML
    private Label fNameValidation;
    @FXML
    private Label lNameValidation;
    @FXML
    private Label passwordValidation;
    @FXML
    private Label userNameValidation;
    @FXML
    private Label emailValidation;
    String errorStyle = "-fx-border-color: #F56457; -fx-border-width: 2px; -fx-border-radius:12px";
    String resetStyle = "-fx-border-color: #ffffff; -fx-border-width: 2px; -fx-border-radius:12px";


    private static String firstName, lastName, emailField, userName, userPassword, confirmPassword;
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    public void nextForm(ActionEvent e) throws IOException{
        firstName = fName.getText();
        lastName = lName.getText();
        emailField = email.getText();
        userName = uName.getText();
        userPassword = password.getText();
        confirmPassword = cPassword.getText();

        if(firstName.isBlank()){
            fNameValidation.setText("! FirstName Cannot Be Empty");
            fName.setStyle(errorStyle);
        }
        else if(firstName.length() < 3){
            fNameValidation.setText("! FirstName Should Contain At-least Three Characters");
            fName.setStyle(errorStyle);

        }
        if(lastName.isBlank()){
            lNameValidation.setText("! LastName Cannot Be Empty");
            lName.setStyle(errorStyle);

        }
        else if(lastName.length() < 3){
            lNameValidation.setText("! LastName Should Contain At-least Three Characters");
            lName.setStyle(errorStyle);

        }
        if(userName.isBlank()){
            userNameValidation.setText("! UserName Cannot Be Empty");
            uName.setStyle(errorStyle);

        }
        if(emailField.isBlank()){
            emailValidation.setText("! Email Cannot Be Empty");
            email.setStyle(errorStyle);

        }
        if(confirmPassword.isBlank()){
            cPassword.setStyle(errorStyle);
        }
        if(!userPassword.equals(confirmPassword)){
            passwordValidation.setText("! Password Doesn't Match");
            password.setStyle(errorStyle);
            cPassword.setStyle(errorStyle);


        }
        else if(userPassword.length() < 8){
            passwordValidation.setText("! Password Should Contain 8 Characters");
            password.setStyle(errorStyle);
            cPassword.setStyle(errorStyle);

        }

        else if (fNameValidation.getText().isBlank() && lNameValidation.getText().equals("") && userNameValidation.getText().equals("") && emailValidation.getText().equals("") && passwordValidation.getText().equals("")){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp_Personal_Info.fxml"));
            obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            obj.scene = new Scene(fxmlLoader.load());
            obj.stage.setScene(obj.scene);
            obj.stage.centerOnScreen();
            obj.stage.show();
        }
    }
    public void previousForm(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        obj.stage.show();
    }
    public void clear(MouseEvent e){
        fName.setStyle(resetStyle);
        cPassword.setStyle(resetStyle);
        password.setStyle(resetStyle);
        email.setStyle(resetStyle);
        uName.setStyle(resetStyle);
        lName.setStyle(resetStyle);

        fNameValidation.setText("");
        lNameValidation.setText("");
        emailValidation.setText("");
        userNameValidation.setText("");
        passwordValidation.setText("");
    }

    /*CLOSE BUTTON LOGIC STARTS HERE ----*/
    @FXML
    public void close(ActionEvent e){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    /*CLOSE BUTTON LOGIC ENDS HERE ----*/

    /*DRAGGING WINDOW LOGIC STARTS HERE -----*/
    @FXML
    public void dragWindow(MouseEvent e)
    {
        obj.stage = (Stage) Main.getScene().getWindow();
        obj.stage.setX(e.getScreenX()-x);
        obj.stage.setY(e.getScreenY()-y);

    }

    @FXML
    public void pressedWindow(MouseEvent e)
    {
        x = e.getSceneX();
        y= e.getSceneY();
    }

    /* DRAGGING WINDOW LOGIC ENDS HERE ----- */
}
