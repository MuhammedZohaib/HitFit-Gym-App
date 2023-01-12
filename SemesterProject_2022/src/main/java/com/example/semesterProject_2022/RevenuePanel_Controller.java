package com.example.semesterProject_2022;

import backend_functions.CustomDate;
import com.mailjet.client.MailjetClient;
import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model_class.Expense;
import model_class.Revenue;

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
    private Label totalMonthlyRevenue;
    @FXML
    private TextArea description;
    @FXML
    private TextField expenseAmount;

    @FXML
    private Label totalMonthlyExpense;

    @FXML
    private Label multBegginer;

    @FXML
    private Label multPro;

    @FXML
    private Label multStarter;

    @FXML
    private Label noOfBegMemberships;

    @FXML
    private Label noOfProMemberships;

    @FXML
    private Label noOfStarterMemberships;

    @FXML
    private Label totalRevenue;

    @FXML
    private Label totalRevenue1;

    //Send this monthly expense to Database
    public static int monthlyExpense;



    private String descriptionOfExpense;
    private LocalDate expenseDate;
    private String expenseAmountToDb;
    private int expenseAmnt;

    public void addExpenseButton() throws IOException {
        new GeneralFunctions().switchSceneModality("AddExpense.fxml");
//        new GeneralFunctions().switchScene("AddExpense.fxml");
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

             try{

                 Expense expense = new Expense(DatabaseFunctions.generateId("expenses"), descriptionOfExpense, expenseAmnt, Date.valueOf(expenseDate));
                 DatabaseFunctions.saveToDb(expense, null);
                 Revenue revenue = new Revenue(DatabaseFunctions.generateId("revenues"), expense.getMonth(), expense.getYear(), -expense.getAmount());
                 DatabaseFunctions.saveUpdateToDb(revenue);

             }catch (Exception error){
                 System.out.println("Connection not established and Data not saved");
             }

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
        try {

            int[] numberOfMemberships = new int[3];
            numberOfMemberships = DatabaseFunctions.getNumberOfMemberships();

            int beginnerMemberships = numberOfMemberships[0];
            int starterMemberships = numberOfMemberships[1];
            int proMemberships = numberOfMemberships[2];

            int totalCurrentExpense = DatabaseFunctions.getCurrentMonthExpense();

            //TODO number of beginner db will set here
            noOfBegMemberships.setText(String.valueOf(beginnerMemberships));
            //TODO number of starter db will set here
            noOfStarterMemberships.setText(String.valueOf(starterMemberships));
            //TODO number of pro db will set here
            noOfProMemberships.setText(String.valueOf(proMemberships));
            //TODO replace 20 with number of beginner db
            int beginnerRevenue = beginnerMemberships * 2000;
            multBegginer.setText(String.valueOf(beginnerRevenue));
            //TODO replace 30 with number of starter db
            int starterRevenue = starterMemberships * 3000;
            multStarter.setText(String.valueOf(starterRevenue));
            //TODO replace 30 with number of pro db
            int proRevenue = proMemberships * 4500;
            multPro.setText(String.valueOf(proRevenue));
            int totalRevenue = beginnerRevenue + starterRevenue + proRevenue;
            totalMonthlyRevenue.setText(String.valueOf(totalRevenue));
            //TODO set Monthly expense from db here
            totalMonthlyExpense.setText(String.valueOf(totalCurrentExpense));
        }
        catch (Exception e){
            System.out.println(e);
        }


    }
}
