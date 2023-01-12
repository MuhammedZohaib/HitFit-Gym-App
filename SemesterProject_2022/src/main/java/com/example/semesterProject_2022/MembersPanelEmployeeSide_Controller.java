package com.example.semesterProject_2022;

import com.mailjet.client.resource.User;
import database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model_class.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class MembersPanelEmployeeSide_Controller implements Initializable {

    // Making the field public so, it can be accessible without getter setters
    public static int deletingId=-1;

    private final static int DataSize = 100;
    private final static int rowsPerPage = 10;
    @FXML
    private Pagination pagination;

    Stage membercardstage;

    Customer customer = null;

    private String FullName;
    @FXML
    private TableColumn<Customer, Integer> Id;
    @FXML
    private TableColumn<Customer, CustomMenuButton> action;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    public TableView<Customer> membersView1;

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

    @FXML
    private TextField keyword;

    public static ObservableList<Customer> memberslist = FXCollections.observableArrayList();
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Button*/

        pagination.setPageFactory(this::createPage);

        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*------Searching With Keryword Logic----------*/
        FilteredList<Customer> filteredList = new FilteredList<>(memberslist,b -> true);

        keyword.textProperty().addListener((observable,oldvalue,newvalue) ->
        {

            filteredList.setPredicate(customer -> {
                if(newvalue.isEmpty() || newvalue.isBlank() || newvalue==null)
                {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                if(customer.getFullname().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                }
                else if(customer.getFirstName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if (customer.getLastName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(customer.getPhoneNumber().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(customer.getNicNumber().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(customer.getId()).indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if((String.valueOf(customer.getMonthlyPlan()).toLowerCase().indexOf(searchkeyword) > -1))
                {
                    return true;
                }  else if (customer.getEmail().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else
                {
                    return false;
                }

            });


            SortedList<Customer> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(membersView1.comparatorProperty());
            membersView1.setItems(sortedList);
        });
        /*----Search with Keyword Logic Ends HERE---------*/

    }

    @FXML
    void sortbtn(ActionEvent event) {

        memberslist.sort(Comparator.comparing(Customer::tolowerfirstname, Comparator.naturalOrder()));
        membersView1.setItems(memberslist);

    }
    private Node createPage(int pageIndex) {
        if(memberslist.size()>0 && memberslist.size()<=10) {
            pagination.setPageCount(1);
        } else if(memberslist.size()>10 && memberslist.size()<=20)
        {
            pagination.setPageCount(2);
        } else if(memberslist.size()>20 && memberslist.size()<=30)
        {
            pagination.setPageCount(3);
        } else if(memberslist.size()>30 && memberslist.size()<=40)
        {
            pagination.setPageCount(4);
        } else if(memberslist.size()>40 && memberslist.size()<=50)
        {
            pagination.setPageCount(5);
        } else if(memberslist.size()>50 && memberslist.size()<=60)
        {
            pagination.setPageCount(6);
        } else if(memberslist.size()>60 && memberslist.size()<=70)
        {
            pagination.setPageCount(7);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, memberslist.size());
        try{
            membersView1.setItems(FXCollections.observableList(memberslist.subList(fromIndex, toIndex)));
        }
        catch (Exception e){
            System.out.println("Not Enough Entries");
        }
        return membersView1;
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
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        plan.setCellValueFactory(new PropertyValueFactory<>("monthlyPlan"));
        action.setCellValueFactory(new PropertyValueFactory<>("actionBtn"));
    }

    void showrecords() throws SQLException {
        memberslist.clear();
        try {
            resultSet = DatabaseFunctions.getAllCustomers();

            System.out.println(resultSet);
            while (resultSet.next()) {
                memberslist.add(new Customer(false,resultSet.getInt("id"),resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("nic"), Integer.parseInt(resultSet.getString("monthly_plan")), new CustomMenuButton(resultSet.getInt("id"), "Action", resultSet.getString("first_name")+" "+resultSet.getString("last_name"),resultSet.getString("weight"),"XYZ",resultSet.getString("email"),resultSet.getString("username"),resultSet.getString("monthly_plan"))));

                membersView1.setItems(memberslist);
            }
        }

        catch (NullPointerException e){
            System.out.println("Connection to Database Cannot Be Established");
        }
    }
    @FXML
    void sortbtn1(ActionEvent event) {
        memberslist.sort(Comparator.comparing(Customer::getId, Comparator.naturalOrder()));
        membersView1.setItems(memberslist);

    }

    @FXML
    void refreshbtn(ActionEvent event) throws SQLException {

        keyword.setText("");
        showrecords();

    }


}
