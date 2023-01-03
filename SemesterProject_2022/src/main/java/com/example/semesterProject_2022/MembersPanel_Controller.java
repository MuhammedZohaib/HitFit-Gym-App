package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model_class.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MembersPanel_Controller implements Initializable {

    private final static int DataSize = 100;
    private final static int rowsPerPage = 5;
    @FXML
    private Pagination pagination;

    Stage membercardstage;

    Customer customer = null;

    private String FullName;
    @FXML
    private TableColumn<Customer, MenuButton> action;

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
        pagination.setPageFactory(this::createPage);

        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, memberslist.size());
        membersView.setItems(FXCollections.observableList(memberslist.subList(fromIndex, toIndex)));
        return membersView;
    }

    public void view() throws IOException {
        membercardstage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("membersDetailCard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        membercardstage.setScene(scene);
        membercardstage.initStyle(StageStyle.UNDECORATED);
        membercardstage.initModality(Modality.APPLICATION_MODAL);
        membercardstage.showAndWait();
        membercardstage.centerOnScreen();
    }
    public void loadData() throws SQLException {
        showrecords();
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        plan.setCellValueFactory(new PropertyValueFactory<>("monthlyPlan"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

     void showrecords() throws SQLException {
        memberslist.clear();
         try {
             resultSet = DatabaseFunctions.getAllCustomers();


             while (resultSet.next()) {
                 memberslist.add(new Customer(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("nic"), Integer.parseInt(resultSet.getString("monthly_plan"))));
                // membersView.setItems(memberslist);
             }
         }

         catch (NullPointerException e){
                 System.out.println("Connection to Database Cannot Be Established");
             }
    }
}
