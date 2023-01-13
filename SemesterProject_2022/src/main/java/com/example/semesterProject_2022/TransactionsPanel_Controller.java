package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.Comparator;
import java.util.ResourceBundle;

public class TransactionsPanel_Controller implements Initializable {
    private final static int rowsPerPage = 10;

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
    void refreshbtn(ActionEvent event) throws SQLException {
        keyword.setText("");
        showrecords();
    }

    @FXML
    void sortbtn(ActionEvent event) {
        TransactionsList.sort(Comparator.comparing(Transaction::getLowerCaseName, Comparator.naturalOrder()));
        transactionView.setItems(TransactionsList);
    }

    @FXML
    void sortbtn1(ActionEvent event) {
        TransactionsList.sort(Comparator.comparing(Transaction::getTransactionId, Comparator.naturalOrder()));
        transactionView.setItems(TransactionsList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pagination.setPageFactory(this::createPage);

        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*------Searching With Keryword Logic----------*/
        FilteredList<Transaction> filteredList = new FilteredList<>(TransactionsList, b -> true);

        keyword.textProperty().addListener((observable,oldvalue,newvalue) ->
        {

            filteredList.setPredicate(transaction -> {
                if(newvalue.isEmpty() || newvalue.isBlank() || newvalue==null)
                {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                if(transaction.getAccountOwnerName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if (transaction.getBankName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(transaction.getStringStatus().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(transaction.getAmount()).indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(transaction.getTransactionId()).indexOf(searchkeyword) > -1)
                {
                    return true;
                } else
                {
                    return false;
                }

            });
            SortedList<Transaction> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(transactionView.comparatorProperty());
            transactionView.setItems(sortedList);
    });

    }

    private Node createPage(int pageIndex) {
        if(TransactionsList.size()>0 && TransactionsList.size()<=10) {
            pagination.setPageCount(1);
        } else if(TransactionsList.size()>10 && TransactionsList.size()<=20)
        {
            pagination.setPageCount(2);
        } else if(TransactionsList.size()>20 && TransactionsList.size()<=30)
        {
            pagination.setPageCount(3);
        } else if(TransactionsList.size()>30 && TransactionsList.size()<=40)
        {
            pagination.setPageCount(4);
        } else if(TransactionsList.size()>40 && TransactionsList.size()<=50)
        {
            pagination.setPageCount(5);
        } else if(TransactionsList.size()>50 && TransactionsList.size()<=60)
        {
            pagination.setPageCount(6);
        } else if(TransactionsList.size()>60 && TransactionsList.size()<=70)
        {
            pagination.setPageCount(7);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, TransactionsList.size());
        try{
            transactionView.setItems(FXCollections.observableList(TransactionsList.subList(fromIndex, toIndex)));
        }
        catch (Exception e){
            System.out.println("Not Enough Entries");
        }
        return transactionView;
    }

    private void loadData() throws SQLException {
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
                TransactionsList.add(new Transaction(resultSet.getBoolean(8),resultSet.getInt(1),resultSet.getDate(2), resultSet.getInt(3),resultSet.getString(5),resultSet.getString(6), new CustomMenuButton("Action",resultSet.getInt(1))));

                transactionView.setItems(TransactionsList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
