package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AccontSettingsPanel_Controller implements Initializable {

    @FXML
    private TextField editEmail;

    @FXML
    private TextField editName;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editPhoneNumber;

    @FXML
    private Text emailField;

    @FXML
    private Text nameField;

    @FXML
    private Text phoneField;


    @FXML
    private Button save;

    String newName, newPhoneNumber, newEmail, newPassword;

    @FXML
    void editEmailButton(ActionEvent event) {
        editEmail.setVisible(true);
        emailField.setVisible(false);
    }
    @FXML
    public void setData(KeyEvent e){
        newEmail = editEmail.getText();
        newName = editName.getText();
        newPhoneNumber = editPhoneNumber.getText();
        newPassword = editPassword.getText();

        if(e.getCode() == KeyCode.ENTER){
            if(!newName.isEmpty() && !newName.isBlank()) {
                nameField.setText(newName);
                nameField.setVisible(true);
                editName.setVisible(false);
            }
            else {
                nameField.setText("Ayaan");
            }
            if(!newEmail.isBlank() && !newEmail.isEmpty()) {
                emailField.setText(newEmail);
                emailField.setVisible(true);
                editEmail.setVisible(false);
            }
            else {
                emailField.setText("test@gmail.com");
            }
            if(!newPhoneNumber.isBlank() && !newPhoneNumber.isEmpty()) {
                phoneField.setText(newPhoneNumber);
                phoneField.setVisible(true);
                editPhoneNumber.setVisible(false);
            }
            else {
                phoneField.setText("0300 0000000");
            }
            editPassword.setVisible(false);
            }
    }

    @FXML
    void editNameButton(ActionEvent event) {
        editName.setVisible(true);
        nameField.setVisible(false);
        editName.setText("");
    }

    @FXML
    void editPhoneButton(ActionEvent event) {
        editPhoneNumber.setVisible(true);
        phoneField.setVisible(false);
        editPhoneNumber.setText("");
    }

    @FXML
    void updatePasswordButton(ActionEvent event) {
        editPassword.setVisible(true);
        editPassword.setText("");

    }
    @FXML
    void Save() {
        newEmail = editEmail.getText();
        newName = editName.getText();
        newPhoneNumber = editPhoneNumber.getText();
        newPassword = editPassword.getText();

        editEmail.setVisible(false);
        editName.setVisible(false);
        editPhoneNumber.setVisible(false);
        editPassword.setVisible(false);
        emailField.setVisible(true);
        nameField.setVisible(true);
        phoneField.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Get Data from Database on basis of username and set here
            nameField.setText(newName);
            emailField.setText(newEmail);
            phoneField.setText(newPhoneNumber);
    }
}
