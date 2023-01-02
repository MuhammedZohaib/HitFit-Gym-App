package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;

public class RevenuePanel_Controller {

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

    private String descriptionOfExpense;
    private LocalDate expenseDate;
    private String expenseAmountToDb;

    public void addExpenseButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(RevenuePanel_Controller.class.getResource("AddExpense.fxml"));
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }
    public void closeExpense(){
        new GeneralFunctions().close(exit);
    }
    public void saveExpenseButton(ActionEvent e){
         descriptionOfExpense = description.getText();
         expenseDate = dateOfExpense.getValue();
         expenseAmountToDb = expenseAmount.getText();

         if(descriptionOfExpense.isBlank() || descriptionOfExpense.isEmpty()){
             description.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;");
         }
         if(expenseAmountToDb.isEmpty() || expenseAmountToDb.isBlank()){
             expenseAmount.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px;");
         }
         else {
             closeExpense();
             System.out.println(descriptionOfExpense);
             System.out.println(expenseDate);
             System.out.println(expenseAmountToDb);
         }
    }
    public void resetStyle(){
        description.setStyle("-fx-border-color: #ffffff;");
        expenseAmount.setStyle("-fx-border-color: #ffffff;");
    }

}
