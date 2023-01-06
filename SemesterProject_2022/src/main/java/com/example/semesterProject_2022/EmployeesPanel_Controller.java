package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeesPanel_Controller {

    @FXML
    private TableColumn<?, ?> Designation;

    @FXML
    private TableColumn<?, ?> FirstName;

    @FXML
    private TableColumn<?, ?> Id;

    @FXML
    private TableColumn<?, ?> LastName;

    @FXML
    private TableColumn<?, ?> Salary;

    @FXML
    private TableColumn<?, ?> action;

    @FXML
    private Button addbutton;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableView<?> employeesView;

    @FXML
    private TextField keyword;

    @FXML
    private TableColumn<?, ?> nic;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private Button refreshButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button sortButton1;

    @FXML
    void addEmployee(ActionEvent event) {

    }

    @FXML
    void refreshbtn(ActionEvent event) {

    }

    @FXML
    void sortbtn(ActionEvent event) {

    }

    @FXML
    void sortbtn1(ActionEvent event) {

    }

}
