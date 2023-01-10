package com.example.semesterProject_2022;

import backend_functions.Email;
import backend_functions.Password;
import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.security.SecureRandom;

public class ForgetPassword_Controller {

    @FXML
    private TextField CodeField;

    @FXML
    private Button resendButton;
    @FXML
    private AnchorPane topBar;
    @FXML
    private TextField email;
    @FXML
    private Label emailValidation;
    @FXML
    private Label codeValidation;
    @FXML
    private TextField cResetPassword;

    @FXML
    private Button reset;
    @FXML
    private Button exit;

    @FXML
    private TextField resetPassword;

    @FXML
    private Label resetPasswordValidation;
    @FXML
    private Button sendCode;
    @FXML
    private Button verifyBtn;

    static String  userEmail;
    static SecureRandom random = new SecureRandom();
    static int code = random.nextInt(111111, 999999);
    public static int verificationCode = code;

    String errorStyle = "-fx-border-color: #ff0000; -fx-border-width: 3px; -fx-border-radius:8px";
    String resetStyle = "-fx-border-color: transparent; -fx-border-radius:8px";




    public void sendCodeToEmail(ActionEvent e) throws IOException {
        userEmail = email.getText();
        if(Email.checkEmail(userEmail) && !userEmail.isBlank() && !userEmail.isEmpty()){
            new Email().sendPasswordResetEmail(userEmail);
            new GeneralFunctions().switchScene(e,"PasswordReset_Verification.fxml");
        }
        else{
            emailValidation.setText("Invalid Email or No Account exist on this email");
            email.setStyle(errorStyle);
        }
    }

    public void verify(ActionEvent e) throws IOException {
        String userEnteredCode = CodeField.getText();
        int code1;
        try{
            code1 = Integer.parseInt(userEnteredCode);
            if (code1 == verificationCode){
                new GeneralFunctions().switchScene(e,"SetNewCustomerPassword.fxml");
            }
            else {
                codeValidation.setText("Code Doesn't Match");
                CodeField.setStyle(errorStyle);
                CodeField.setText("");
            }
        }
        catch (Exception error){
            codeValidation.setText("Invalid code");
            CodeField.setStyle(errorStyle);
            CodeField.setText("");
        }

    }
    public void resetCustomerPassword(){
        String password = resetPassword.getText();
        String cPassword = cResetPassword.getText();

        if(password.isBlank() || password.isEmpty()){
            resetPasswordValidation.setText("Password Field cannot be empty");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if(password.length()<8 ){
            resetPasswordValidation.setText("Password must contain at-least 8 characters");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if(!password.equals(cPassword)){
            resetPasswordValidation.setText("Password Doesn't Match");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if (resetPasswordValidation.getText().equals("")) {
            String[] hashedPassword;
            hashedPassword = Password.makeFinalPassword(password);
            DatabaseFunctions.updateCustomerPassword(userEmail,hashedPassword);
/*
            System.out.println(userEmail);
            System.out.println(password);
*/
            close();
        }
    }public void resetEmployeePassword(){
        String password = resetPassword.getText();
        String cPassword = cResetPassword.getText();

        if(password.isBlank() || password.isEmpty()){
            resetPasswordValidation.setText("Password Field cannot be empty");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if(password.length()<8 ){
            resetPasswordValidation.setText("Password must contain at-least 8 characters");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if(!password.equals(cPassword)){
            resetPasswordValidation.setText("Password Doesn't Match");
            resetPassword.setStyle(errorStyle);
            cResetPassword.setStyle(errorStyle);
        }
        else if (resetPasswordValidation.getText().equals("")) {
            String[] hashedPassword;
            hashedPassword = Password.makeFinalPassword(password);
            DatabaseFunctions.updateEmployeePassword(userEmail,hashedPassword);
            close();
        }
    }
    public void close(){
        new GeneralFunctions().close(exit);
    }

    public void resetStyle1(){
            emailValidation.setText("");
            email.setStyle(resetStyle);
    }
    public void resetStyle2(){
            codeValidation.setText("");
            CodeField.setStyle(resetStyle);
    }
    public void resetStyle3(){
            resetPasswordValidation.setText("");
            resetPassword.setStyle(resetStyle);
            cResetPassword.setStyle(resetStyle);
    }


}
