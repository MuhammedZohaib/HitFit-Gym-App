package com.example.semesterProject_2022;

import backend_functions.Email;
import backend_functions.Password;
import backend_functions.Username;
import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model_class.Employee;

import java.sql.Date;
import java.time.LocalDate;

import static backend_functions.Email.validateEmail;


public class AddEmployeeController {
    @FXML
    private AnchorPane Main;
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
    private Label fNameValidation;
    @FXML
    private Label lNameValidation;
    @FXML
    private Label pNumberValidation;
    @FXML
    private Label cnicValidation;
    @FXML
    private Label dateValidation;

    @FXML
    private Label emailValidation;

    @FXML
    private Label designationValidation;
    @FXML
    private Label passwordValidation;
    @FXML
    private Label salaryValidation;
    @FXML
    private Label uNameValidation;
    @FXML
    private Button exit;
    @FXML
    private StackPane stackpane;
    private  String fName;
    private  String lName;
    private  String pNumber;
    private  String cnic;
    private  String gender;
    private  LocalDate joiningDate;
    private  String username;
    private  String userEmail;
    private  String password;
    private  String salary;
    private static String designation;

    private double x=0 ,y = 0;
    GeneralFunctions generalFunctions = new GeneralFunctions();

    String errorStyle = "-fx-border-color: #ff0000; -fx-border-width: 3px; -fx-border-radius:10px";
    String resetStyle = "-fx-border-color: transparent; -fx-border-width: 3px;  -fx-border-radius:10px";
    String alphabetRegex = "^[a-zA-Z ]*$";
    String numericRegex = "^[0-9]*$";
    @FXML
    void nextActionButton() {
        fName = firstnamefield.getText();
        lName = lastnamefield.getText();
        pNumber = phonenofield.getText();
        cnic = nicfield.getText();
        joiningDate = joindatefield.getValue();


        if(maleradiobutton.isSelected()){
            gender = "male";
        }
        else if (femaleradiobutton.isSelected()){
            gender = "female";
        }

        if(fName.isBlank() || fName.isEmpty()){
            fNameValidation.setText("! FirstName Cannot Be Empty");
            firstnamefield.setStyle(errorStyle);
        }
        else if(fName.length() < 3){
            fNameValidation.setText("! FirstName Should Contain At-least Three Characters");
            firstnamefield.setStyle(errorStyle);
        }
        else if (!fName.matches(alphabetRegex)) {
            fNameValidation.setText("! FirstName cannot contain letters");
            firstnamefield.setStyle(errorStyle);
        }
        if(lName.isBlank() || lName.isEmpty()){
            lNameValidation.setText("! LastName Cannot Be Empty");
            lastnamefield.setStyle(errorStyle);
        }
        else if(lName.length() < 3){
            lNameValidation.setText("! LastName Should Contain At-least Three Characters");
            lastnamefield.setStyle(errorStyle);
        }
        else if (!lName.matches(alphabetRegex)) {
            lNameValidation.setText("! lastName cannot contain letters");
            lastnamefield.setStyle(errorStyle);
        }
        if(pNumber.isBlank() || pNumber.isEmpty()){
            pNumberValidation.setText("! PhoneNumber cannot be empty");
            phonenofield.setStyle(errorStyle);
        }
        else if (!pNumber.matches(numericRegex)) {
            pNumberValidation.setText("! PhoneNumber cannot contain letters");
            phonenofield.setStyle(errorStyle);
        }
        else if(pNumber.length() != 11){
            pNumberValidation.setText("! PhoneNumber must contain exactly 11 digits");
            phonenofield.setStyle(errorStyle);
        }

        if(cnic.isBlank() || cnic.isEmpty()){
            cnicValidation.setText("! NIC cannot be cannot be empty");
            nicfield.setStyle(errorStyle);
        }
        else if(cnic.length() != 13){
            cnicValidation.setText("! NIC must contain exactly 13 digits");
            nicfield.setStyle(errorStyle);
        }
        else if (!cnic.matches(numericRegex)) {
            cnicValidation.setText("! NIC cannot contain letters");
            nicfield.setStyle(errorStyle);
        }
        try{
            if(joiningDate.equals(null)){
                dateValidation.setText("! Joining Date cannot be empty");
                joindatefield.setStyle(errorStyle);
            }
        }
        catch (NullPointerException e){
            dateValidation.setText("!  Joining Date cannot be empty");
        }
        if(fNameValidation.getText().equals("") && lNameValidation.getText().equals("") && pNumberValidation.getText().equals("") && cnicValidation.getText().equals("") && dateValidation.getText().equals("")){
            AccountInfopane.toBack();
            personalInfo.toFront();
            stackpane.getChildren().get(1).setVisible(false);
            stackpane.getChildren().get(0).setVisible(true);
        }
    }
    public void addEmployee(){
        username = Usernamefield.getText();
        userEmail = emailfield.getText();
        password = passwordfield.getText();
        salary = salaryfield.getText();
        Boolean apiResponse = null;

        if(!userEmail.isBlank() && !userEmail.isEmpty() && DatabaseFunctions.makeConnection() == true){
            apiResponse = validateEmail(userEmail);
        }
        if(username.isBlank()){
            uNameValidation.setText("! UserName Cannot Be Empty");
            Usernamefield.setStyle(errorStyle);
        }
        try{
                if (Username.checkUsername(username)){
                uNameValidation.setText("! UserName Already Exists");
                Usernamefield.setStyle(errorStyle);
            }
        }catch (Exception e){
            System.out.println("No Connection");
        }

        if(userEmail.isBlank()){
            emailValidation.setText("! Email Cannot Be Empty");
            emailfield.setStyle(errorStyle);
        }try{
            if (Email.checkEmail(userEmail)){
                emailValidation.setText("! Email Already Exists");
                emailfield.setStyle(errorStyle);
            }
        }
        catch (Exception e){
            System.out.println("No Connection to check email validation");
        }

        if(apiResponse.equals(false)){
            emailValidation.setText("! Invalid Email");
            emailfield.setStyle(errorStyle);
        }
        if(designation == null){
            designationValidation.setText("! Please select a designation");
        }
        if(salary.isEmpty() || salary.isBlank()){
            salaryValidation.setText("! Salary Cannot Be Empty");
            salaryfield.setStyle(errorStyle);
        }
        else if(!salary.matches(numericRegex)){
            salaryValidation.setText("! Salary Cannot be in Letters");
            salaryfield.setStyle(errorStyle);
        }
        if(password.isBlank() || password.isEmpty()){
            passwordValidation.setText("! Password cannot be empty");
            passwordfield.setStyle(errorStyle);
        }
        if(password.length() < 8){
            passwordValidation.setText("! Password must contain at-least 8 letters");
            passwordfield.setStyle(errorStyle);
        }
        if(uNameValidation.getText().equals("") && emailValidation.getText().equals("") && passwordValidation.getText().equals("") && salaryValidation.getText().equals("") && designationValidation.getText().equals("") && Boolean.TRUE.equals(apiResponse)){
            close();
            String[] tempArr;
            tempArr = Password.makeFinalPassword(password);

            Employee employee = new Employee(Date.valueOf(joiningDate), fName, lName, userEmail, pNumber, cnic, designation, Integer.parseInt(salary), DatabaseFunctions.generateId("employees"), gender, username, tempArr[1], tempArr[0]);
            try{
                DatabaseFunctions.saveToDb(employee);
            }
            catch (Exception e){
                System.out.println("No Connection to Database, Data Not Saved");
            }

        }
        emailValidation.setText("");

    }
    public void reset(){
        fNameValidation.setText("");
        lNameValidation.setText("");
        pNumberValidation.setText("");
        cnicValidation.setText("");
        dateValidation.setText("");

        firstnamefield.setStyle(resetStyle);
        lastnamefield.setStyle(resetStyle);
        phonenofield.setStyle(resetStyle);
        nicfield.setStyle(resetStyle);
        joindatefield.setStyle(resetStyle);
    }
    public void reset2(){
        uNameValidation.setText("");
        emailValidation.setText("");
        passwordValidation.setText("");
        designationValidation.setText("");
        salaryValidation.setText("");

        Usernamefield.setStyle(resetStyle);
        emailfield.setStyle(resetStyle);
        passwordfield.setStyle(resetStyle);
        salaryfield.setStyle(resetStyle);
    }

    public void close(){
        new GeneralFunctions().modalityClose(exit);
    }
    @FXML
    void supervisor() {
        designation = "Supervisor";
        designationmenubutton.setText("Supervisor");
    }
    @FXML
    void trainer() {
        designation = "Trainer";
        designationmenubutton.setText("Trainer");
    }
    @FXML
    void stockManager() {
        designation = "Stock Manager";
        designationmenubutton.setText("Stock Manager");

    }
    @FXML
    void cleaningStaff() {
        designation = "Cleaning Staff";
        designationmenubutton.setText("Cleaning Staff");

    }
    @FXML
    void equipmentManager() {
        designation = "Equipment Maintainer";
        designationmenubutton.setText("Equipment Maintainer");
    }
    public void dragWindow(MouseEvent e)
    {
       generalFunctions.stage = (Stage) Main.getScene().getWindow();

        generalFunctions.stage.setX(e.getScreenX()-x);

        generalFunctions.stage.setY(e.getScreenY()-y);
    }

    public void pressedWindow(MouseEvent e)
    {
        x = e.getSceneX();
        y= e.getSceneY();
    }


}
