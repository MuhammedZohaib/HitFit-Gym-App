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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp_Controller {

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
    private Text Texts;

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


    private static String firstName, lastName, emailField, userName, userPassword, confirmPassword;
    LoginSignUp_Controller obj = new LoginSignUp_Controller();
    public void nextForm(ActionEvent e) throws IOException {
        firstName = fName.getText();
        lastName = lName.getText();
        emailField = email.getText();
        userName = uName.getText();
        userPassword = password.getText();
        confirmPassword = cPassword.getText();

        if(firstName.equals("")){
            fNameValidation.setText("FirstName Cannot Be Empty");
        }
        else if(firstName.length() < 3){
            fNameValidation.setText("FirstName Should Contain At-least Three Characters");
        }
        if(lastName.equals("")){
            lNameValidation.setText("LastName Cannot Be Empty");
        }
        else if(lastName.length() < 3){
            lNameValidation.setText("LastName Should Contain At-least Three Characters");
        }
        if(userName.equals("")){
            userNameValidation.setText("UserName Cannot Be Empty");
        }
        if(emailField.equals("")){
            emailValidation.setText("Email Cannot Be Empty");
        }
        if(!userPassword.equals(confirmPassword)){
            passwordValidation.setText("Password Doesn't Match");
        }
        else if(userPassword.length() < 8){
            passwordValidation.setText("Password Should Contain 8 Characters");
        }
        else if (fNameValidation.getText().equals("") && lNameValidation.getText().equals("") && userNameValidation.getText().equals("") && emailValidation.getText().equals("") && passwordValidation.getText().equals("")){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp_Personal_Info.fxml"));
            obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            obj.scene = new Scene(fxmlLoader.load());
            obj.stage.setScene(obj.scene);
            obj.stage.centerOnScreen();
            obj.stage.show();
        }
    }
    public void setText(){
        fName = new TextField();
        lName = new TextField();
        email = new TextField();
        uName = new TextField();
        password = new TextField();
        cPassword = new PasswordField();

        fName.setText(firstName);
        lName.setText(lastName);
        uName.setText(userName);
        email.setText(emailField);
        password.setText(userPassword);
        cPassword.setText(confirmPassword);
    }
    public void previousForm(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
        obj.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        obj.scene = new Scene(fxmlLoader.load());
        obj.stage.setScene(obj.scene);
        obj.stage.centerOnScreen();
        obj.stage.show();
        setText();
    }
    public void clear(MouseEvent e){
        fNameValidation.setText("");
        lNameValidation.setText("");
        emailValidation.setText("");
        userNameValidation.setText("");
        passwordValidation.setText("");
    }


}
