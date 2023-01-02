package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model_class.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MembersPanel_Controller implements Initializable {
    Customer customer = null;
    private String FullName;
    @FXML
    private TableColumn<Customer, Button> action;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableView<Customer> membersView;

    @FXML
    private TableColumn<Customer, String> FirstName;
    @FXML
    private TableColumn<Customer, String> LastName;

    @FXML
    private TableColumn<Customer, String> nic;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, Integer> plan;

    ObservableList<Customer> memberslist = FXCollections.observableArrayList();
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData() throws SQLException {
        showrecords();
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        plan.setCellValueFactory(new PropertyValueFactory<>("monthlyPlan"));
    }

     void showrecords() throws SQLException {
        memberslist.clear();
         try {
             resultSet = DatabaseFunctions.getAllCustomers();


             while (resultSet.next()) {
                 memberslist.add(new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("nic"), Integer.parseInt(resultSet.getString("monthly_plan"))));
                 membersView.setItems(memberslist);
                 System.out.println(memberslist.get(0));
             }
         }

         catch (NullPointerException e){
                 System.out.println("Connection to Database Cannot Be Established");
             }
    }
}
