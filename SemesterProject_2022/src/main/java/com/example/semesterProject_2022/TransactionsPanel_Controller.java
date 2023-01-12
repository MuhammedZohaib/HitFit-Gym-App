package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model_class.Customer;
import model_class.Transaction;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TransactionsPanel_Controller implements Initializable {

    @FXML
    private TableColumn<Transaction, Integer> Amount;

    @FXML
    private TableColumn<Transaction, String> BankName;

    @FXML
    private TableColumn<Transaction, String> BankOwnerName;

    @FXML
    private TableColumn<Transaction, java.sql.Date> TransactionDate;

    @FXML
    private TableColumn<Transaction, Integer> TransactionId;

    @FXML
    private TableColumn<Transaction, CustomMenuButton> action;

    @FXML
    private TableColumn<Transaction, String> Status;
    @FXML
    private TextField keyword;

    @FXML
    private Pagination pagination;

    @FXML
    private Button refreshButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button sortButton1;

    @FXML
    private TableView<Transaction> transactionView;


    public static ObservableList<Transaction> TransactionsList = FXCollections.observableArrayList();
    ResultSet resultSet = null;

    @FXML
    void refreshbtn(ActionEvent event) {

    }

    @FXML
    void sortbtn(ActionEvent event) {

    }

    @FXML
    void sortbtn1(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            loadData();

    }

    private void loadData() {
        showrecords();
        TransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        BankOwnerName.setCellValueFactory(new PropertyValueFactory<>("accountOwnerName"));
        BankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TransactionDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        action.setCellValueFactory(new PropertyValueFactory<>("actionBtn"));
        Status.setCellValueFactory(new PropertyValueFactory<>("StringStatus"));
    }

    private void showrecords() throws SQLException {
        TransactionsList.clear();
        try {
            resultSet = DatabaseFunctions.getAllTransactions();


            while (resultSet.next()) {
                TransactionsList.add(new Transaction(resultSet.getInt(1),resultSet.getDate(2), resultSet.getInt(3),resultSet.getString(5),resultSet.getString(6), new CustomMenuButton("Action",resultSet.getInt(1)));

                transactionView.setItems(TransactionsList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
