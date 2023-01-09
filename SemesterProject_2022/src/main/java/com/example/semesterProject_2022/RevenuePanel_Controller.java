package com.example.semesterProject_2022;

import com.mailjet.client.MailjetClient;
import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model_class.Expense;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class RevenuePanel_Controller implements Initializable {

    @FXML
    AnchorPane expensePane;
    @FXML
    private Button exit;
    @FXML
    private DatePicker dateOfExpense;
    @FXML
    private TextArea description;
    @FXML
    private TextField expenseAmount;

    //Send this monthly expense to Database
    public static int monthlyExpense;

    private String descriptionOfExpense;
    private LocalDate expenseDate;
    private String expenseAmountToDb;
    private int expenseAmnt;

    public void addExpenseButton() throws IOException {
        new GeneralFunctions().switchSceneModality("AddExpense.fxml");
    }
    public void closeExpense(){
        new GeneralFunctions().close(exit);
    }
    public void saveExpenseButton(ActionEvent e){
         descriptionOfExpense = description.getText();
         expenseDate = dateOfExpense.getValue();
         expenseAmountToDb = expenseAmount.getText();
         try{
             expenseAmnt = Integer.parseInt(expenseAmountToDb);
         }
         catch (NullPointerException exception)
         {
             System.out.println(exception);
         }

         if(descriptionOfExpense.isBlank() || descriptionOfExpense.isEmpty()){
             description.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;");
         }
         if(expenseAmountToDb.isEmpty() || expenseAmountToDb.isBlank()){
             expenseAmount.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;");
         }
         else {

             Expense expense = new Expense(DatabaseFunctions.generateId("expenses"), descriptionOfExpense, expenseAmnt, Date.valueOf(expenseDate));
             DatabaseFunctions.saveToDb(expense, null);

             closeExpense();

             //Database function will be called here which will save expenses to Db.
             System.out.println(descriptionOfExpense);
             System.out.println(expenseDate);
             System.out.println(expenseAmountToDb);

         }
    }
    public void resetStyle(){
        description.setStyle("-fx-border-color: #ffffff;");
        expenseAmount.setStyle("-fx-border-color: #ffffff;");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*
        Calendar cal = Calendar.getInstance();
        if ((cal.get(Calendar.DATE) == 30 || cal.get(Calendar.DATE) == 31) || (cal.get(Calendar.MONTH) == Calendar.FEBRUARY && (cal.get(Calendar.DATE) == 28 || cal.get(Calendar.DATE) == 29))){
         monthlyExpense = 0;
        }
*/
    }
}
