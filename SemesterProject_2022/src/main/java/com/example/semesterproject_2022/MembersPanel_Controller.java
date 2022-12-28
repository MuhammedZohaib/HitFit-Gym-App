package com.example.semesterproject_2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model_class.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MembersPanel_Controller {

    @FXML
    private TableView<Customer> membersView;
    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private TableColumn<Customer, Boolean> gender;


    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> nic;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, String> plan;

    public static void setData() throws SQLException {
        ObservableList<Customer> data = FXCollections.observableArrayList();

    }

}
