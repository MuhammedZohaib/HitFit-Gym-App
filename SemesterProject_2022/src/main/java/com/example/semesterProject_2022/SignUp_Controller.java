package com.example.semesterProject_2022;

import backend_functions.CustomDate;
import backend_functions.Email;
import backend_functions.Password;
import backend_functions.Username;
import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model_class.Customer;
import model_class.Transaction;

import java.io.IOException;
import java.time.LocalDate;

import static backend_functions.Email.validateEmail;

public class SignUp_Controller {
    private static String firstName;
    private static String lastName;
    private static String userName;
    private static String emailField;
    private static String userPassword;
    private static String phoneNumber;
    private static String nic;
    private static String userAddress;
    private static String gender;
    private static String userWeight;
    private static LocalDate dob;
    private static  int monthlyPlan;
    private static String nameOfBank;
    private static String userBankAccountName;
    private static String tilID;
    private double x = 0;
    private double y = 0;
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    String errorStyle = "-fx-border-color: #ff0000; -fx-border-width: 3px; -fx-border-radius:12px";
    String resetStyle = "-fx-border-color: transparent; -fx-border-width: 3px; -fx-border-radius:12px";

    String alphabetRegex = "^[a-zA-Z ]*$";
    String numericRegex = "^[0-9]*$";

    @FXML
    private AnchorPane Main;
    @FXML
    private PasswordField cPassword;
    @FXML
    private TextField email;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField password;
    @FXML
    private TextField uName;
    @FXML
    private TextField pNumber;
    @FXML
    private TextField weight;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField cnic;
    @FXML
    private TextField address;
    @FXML
    private DatePicker date;
    @FXML
    private TextField transactionID;
    @FXML
    private TextField accountName;
    @FXML
    private TextField bankName;
    @FXML
    private MenuButton package_select;
    @FXML
    private Button exit;
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
    @FXML
    private Label dateValidation;
    @FXML
    private Label nicValidation;
    @FXML
    private Label phoneNoValidation;
    @FXML
    private Label weightValidation;
    @FXML
    private Label bankNameValidation;

    @FXML
    private Label accountNameValidation;

    @FXML
    private Label tilIDValidation;

    @FXML
    private Label packageValidation;
    private Boolean apiResponse = null;

    public void nextForm(ActionEvent e) throws IOException {
        firstName = fName.getText();
        lastName = lName.getText();
        emailField = email.getText();
        userName = uName.getText();
        userPassword = password.getText();
        String confirmPassword = cPassword.getText();



        if(!emailField.isBlank() && !emailField.isEmpty()){
            apiResponse = validateEmail(emailField);
        }

        if(firstName.isBlank() || firstName.isEmpty()){
            fNameValidation.setText("! FirstName Cannot Be Empty");
            fName.setStyle(errorStyle);
        }
        else if(firstName.length() < 3){
            fNameValidation.setText("! FirstName Should Contain At-least Three Characters");
            fName.setStyle(errorStyle);
        }
        else if (!firstName.matches(alphabetRegex)) {
            fNameValidation.setText("! FirstName cannot contain letters");
            fName.setStyle(errorStyle);
        }
        if(lastName.isBlank() || lastName.isEmpty()){
            lNameValidation.setText("! LastName Cannot Be Empty");
            lName.setStyle(errorStyle);
        }
        else if(lastName.length() < 3){
            lNameValidation.setText("! LastName Should Contain At-least Three Characters");
            lName.setStyle(errorStyle);
        }
        else if (!lastName.matches(alphabetRegex)) {
            lNameValidation.setText("! lastName cannot contain letters");
            lName.setStyle(errorStyle);
        }
        if(userName.isBlank() || userName.isEmpty()){
            userNameValidation.setText("! UserName Cannot Be Empty");
            uName.setStyle(errorStyle);
        }
        else if (Username.checkUsername(userName)){
            System.out.println("Duplicate username");
            userNameValidation.setText("! UserName Already Exists");
            uName.setStyle(errorStyle);
        }
        if(emailField.isBlank() || emailField.isEmpty()){
            emailValidation.setText("! Email Cannot Be Empty");
            email.setStyle(errorStyle);
        }
        else if (Email.checkEmail(emailField)){
            System.out.println("Duplicate email");
            emailValidation.setText("! Email Already Exists");
            email.setStyle(errorStyle);
        }
        else if(apiResponse.equals(false)){
            emailValidation.setText("! Invalid Email");
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
        else if (fNameValidation.getText().isBlank() && lNameValidation.getText().equals("") && userNameValidation.getText().equals("") && emailValidation.getText().equals("") && passwordValidation.getText().equals("") && apiResponse.equals(true)){
            new GeneralFunctions().switchScene(e,"SignUp_Personal_Info.fxml");
        }
    }

    public void paymentForm(ActionEvent e) throws IOException {
        phoneNumber = pNumber.getText();
        nic = cnic.getText();
        userAddress = address.getText();
        dob = date.getValue();
        userWeight = weight.getText();

        if(male.isSelected()){
            gender = "male";
        }
        if(female.isSelected()){
            gender = "female";
        }
        if(userAddress.isEmpty()){
            userAddress = "-";
        }
        if(phoneNumber.isBlank() || phoneNumber.isEmpty()){
            phoneNoValidation.setText("! PhoneNumber cannot be empty");
            pNumber.setStyle(errorStyle);
        }
        else if (!phoneNumber.matches(numericRegex)) {
            phoneNoValidation.setText("! PhoneNumber cannot contain letters");
            pNumber.setStyle(errorStyle);
        }
        else if(phoneNumber.length() != 11){
            phoneNoValidation.setText("! PhoneNumber must contain exactly 11 digits");
            pNumber.setStyle(errorStyle);
        }

        if(nic.isBlank()){
            nicValidation.setText("! NIC cannot be cannot be empty");
            cnic.setStyle(errorStyle);
        }
        else if(nic.length() != 13){
            nicValidation.setText("! NIC must contain exactly 13 digits");
            cnic.setStyle(errorStyle);
        }
        else if (!nic.matches(numericRegex)) {
            nicValidation.setText("! NIC cannot contain letters");
            cnic.setStyle(errorStyle);
        }
        if(userWeight.equals("0")){
            weightValidation.setText("! Invalid weight");
            weight.setStyle(errorStyle);
        }
        else if (userWeight.isBlank() || userWeight.isEmpty()) {
            weightValidation.setText("! Weight Cannot Be empty");
            weight.setStyle(errorStyle);
        }
        else if (!userWeight.matches(numericRegex)) {
            weightValidation.setText("! Weight cannot be in letters");
            weight.setStyle(errorStyle);
        }
        try{
            if(date.equals(null)){
                dateValidation.setText("! Date of Birth cannot be empty");
                date.setStyle(errorStyle);
            }
            else if(dob.getYear() == 2022){
                dateValidation.setText("! Invalid Date of Birth");
            }
        }
        catch (NullPointerException event){
            dateValidation.setText("! Date of Birth cannot be empty");
        }
        if (phoneNoValidation.getText().isBlank() && nicValidation.getText().isBlank() && dateValidation.getText().isBlank() && weightValidation.getText().isBlank()){
            new GeneralFunctions().switchScene(e,"SignUp_Payment_Info.fxml");
        }
    }

    public void doneSignUp(ActionEvent e) throws IOException {
        nameOfBank = bankName.getText();
        tilID = transactionID.getText();
        userBankAccountName = accountName.getText();

        if(nameOfBank.isBlank() || nameOfBank.isEmpty()){
            bankNameValidation.setText("! Bank Name cannot be empty");
            bankName.setStyle(errorStyle);
        }
        else if (!nameOfBank.matches(alphabetRegex)) {
            bankNameValidation.setText("! Bank Name cannot contain Numbers");
            bankName.setStyle(errorStyle);
        }
        if(userBankAccountName.isBlank() || userBankAccountName.isEmpty()){
            accountNameValidation.setText("! Account Holder's Name cannot be empty");
            accountName.setStyle(errorStyle);
        }
        else if (!userBankAccountName.matches(alphabetRegex)) {
            accountNameValidation.setText("! Account Holder's cannot contain Numbers");
            accountName.setStyle(errorStyle);
        }
        if(tilID.isBlank() || tilID.isEmpty()){
            tilIDValidation.setText("! Transaction ID cannot be empty");
            transactionID.setStyle(errorStyle);
        }
        else if (!tilID.matches(numericRegex)) {
            tilIDValidation.setText("! Transaction ID cannot contain Letters");
            transactionID.setStyle(errorStyle);
        }

        if(monthlyPlan == 0){
            packageValidation.setText("! Please Select a Package");
        }
        if(bankNameValidation.getText().equals("") && packageValidation.getText().equals("") && tilIDValidation.getText().equals("") && accountNameValidation.getText().equals("")){

            String[] tempArr;
            tempArr = Password.makeFinalPassword(userPassword);
            // for id generation, use "customer" for getting customer id
            // for id generation, use "transaction" for getting transaction id

            Customer customer = new Customer(firstName,lastName,emailField,gender,phoneNumber,userName, tempArr[1], nic,userAddress,dob.toString(),userWeight,monthlyPlan,DatabaseFunctions.generateId("customers"), tempArr[0]);
            DatabaseFunctions.saveToDb(customer);

            Transaction transaction = new Transaction(DatabaseFunctions.generateId("transactions"), CustomDate.getCurrentDate(), monthlyPlan, tilID, nameOfBank, userBankAccountName, customer.getCustomerId(), false);
            DatabaseFunctions.saveToDb(transaction);

            Email newEmail = new Email();
            newEmail.sendWelcomeEmail(customer.getEmail(), customer.getFirstName() + " " + customer.getLastName());

            tempArr[0] = " ";
            tempArr[1] = " ";

            new GeneralFunctions().switchScene(e,"SignUp_Prompt.fxml");
        }
    }
    public void starter(){
        monthlyPlan = 2000;
        package_select.setText("Starter - Rs.2000");
    }
    public void beginner(){
        monthlyPlan = 3000;
        package_select.setText("Beginner - Rs.3000");
    }
    public void pro(){
        monthlyPlan = 4500;
        package_select.setText("Pro - Rs.4500");
    }
    public void clear(){
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
    public void clearTab2(){
        pNumber.setStyle(resetStyle);
        cnic.setStyle(resetStyle);
        weight.setStyle(resetStyle);

        phoneNoValidation.setText("");
        nicValidation.setText("");
        dateValidation.setText("");
        weightValidation.setText("");
    }

    public void clearTab3(){
        bankName.setStyle(resetStyle);
        accountName.setStyle(resetStyle);
        transactionID.setStyle(resetStyle);

        bankNameValidation.setText("");
        accountNameValidation.setText("");
        tilIDValidation.setText("");
        packageValidation.setText("");
    }
    @FXML
    void GoBackLogIn(ActionEvent e) throws IOException {
        new GeneralFunctions().switchScene(e, "LoginSignUp.fxml");
    }
    @FXML
    public void close(){
        new GeneralFunctions().close(exit);
    }
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

}
