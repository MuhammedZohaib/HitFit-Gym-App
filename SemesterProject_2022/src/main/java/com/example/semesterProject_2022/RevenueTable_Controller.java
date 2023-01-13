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
import model_class.Revenue;
import model_class.Transaction;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class RevenueTable_Controller implements Initializable {

    private final static int rowsPerPage = 10;
    @FXML
    private TableColumn<Revenue,String> Year;

    @FXML
    private TableColumn<Revenue,String> Month;

    @FXML
    private TableColumn<Revenue,Integer> Amount;

    @FXML
    private TableView<Revenue> RevenueView;

    @FXML
    private TableColumn<Revenue,java.sql.Date> RevenueDate;

    @FXML
    private TableColumn<Revenue,Integer> Id;

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
    public static ObservableList<Revenue> RevenueList = FXCollections.observableArrayList();
    ResultSet resultSet = null;

    @FXML
    void refreshbtn(ActionEvent event) {
        keyword.setText("");
        showrecords();
    }

    @FXML
    void sortbtn(ActionEvent event) {

        RevenueList.sort(Comparator.comparing(Revenue::getForMonth, Comparator.naturalOrder()));
        RevenueView.setItems(RevenueList);
    }

    @FXML
    void sortbtn1(ActionEvent event) {
        RevenueList.sort(Comparator.comparing(Revenue::getId, Comparator.naturalOrder()));
        RevenueView.setItems(RevenueList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pagination.setPageFactory(this::createPage);

        loadData();

        /*------Searching With Keryword Logic----------*/
        FilteredList<Revenue> filteredList = new FilteredList<>(RevenueList, b -> true);

        keyword.textProperty().addListener((observable,oldvalue,newvalue) ->
        {

            filteredList.setPredicate(revenue -> {
                if(newvalue.isEmpty() || newvalue.isBlank() || newvalue==null)
                {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                if(revenue.getForMonth().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if (revenue.getForYear().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(revenue.getAmount()).indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(revenue.getId()).indexOf(searchkeyword) > -1)
                {
                    return true;
                } else
                {
                    return false;
                }

            });
            SortedList<Revenue> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(RevenueView.comparatorProperty());
            RevenueView.setItems(sortedList);
        });

    }

    private Node createPage(Integer pageIndex) {
        if(RevenueList.size()>0 && RevenueList.size()<=10) {
            pagination.setPageCount(1);
        } else if(RevenueList.size()>10 && RevenueList.size()<=20)
        {
            pagination.setPageCount(2);
        } else if(RevenueList.size()>20 && RevenueList.size()<=30)
        {
            pagination.setPageCount(3);
        } else if(RevenueList.size()>30 && RevenueList.size()<=40)
        {
            pagination.setPageCount(4);
        } else if(RevenueList.size()>40 && RevenueList.size()<=50)
        {
            pagination.setPageCount(5);
        } else if(RevenueList.size()>50 && RevenueList.size()<=60)
        {
            pagination.setPageCount(6);
        } else if(RevenueList.size()>60 && RevenueList.size()<=70)
        {
            pagination.setPageCount(7);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, RevenueList.size());
        try{
            RevenueView.setItems(FXCollections.observableList(RevenueList.subList(fromIndex, toIndex)));
        }
        catch (Exception e){
            System.out.println("Not Enough Entries");
        }
        return RevenueView;
    }

    private void loadData() {
        showrecords();
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Month.setCellValueFactory(new PropertyValueFactory<>("forMonth"));
        Year.setCellValueFactory(new PropertyValueFactory<>("forYear"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        RevenueDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void showrecords() {
        RevenueList.clear();
        try {
            resultSet = DatabaseFunctions.getAllRevenues();


            while (resultSet.next()) {

                RevenueList.add(new Revenue(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(5),resultSet.getDate(4)));
                RevenueView.setItems(RevenueList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
