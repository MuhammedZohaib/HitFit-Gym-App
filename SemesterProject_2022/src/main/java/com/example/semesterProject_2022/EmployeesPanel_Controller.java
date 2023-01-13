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
import model_class.Employee;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class EmployeesPanel_Controller implements Initializable {

    // Making the field public so, it can be accessible without getter setters
    public static int deletingId=-1;

    public static Employee employee = new Employee();

    private final static int rowsPerPage = 10;
    @FXML
    private TableColumn<Employee, String> Designation;

    @FXML
    private TableColumn<Employee, String> FirstName;

    @FXML
    private TableColumn<Employee, Integer> Id;

    @FXML
    private TableColumn<Employee, String> LastName;

    @FXML
    private TableColumn<Employee, Double> Salary;

    @FXML
    private TableColumn<Employee, CustomMenuButton> action;

    @FXML
    private Button addbutton;

    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, java.util.Date> SelectedDate;
    @FXML
    private TableView<Employee> employeesView;

    @FXML
    private TextField keyword;

    @FXML
    private TableColumn<Employee, String> nic;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<Employee, String> phone;


    @FXML
    private Button refreshButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button sortButton1;

    public static ObservableList<Employee> employeeslist = FXCollections.observableArrayList();
    ResultSet resultSet = null;


    @FXML
    void addEmployee() throws IOException {
        new GeneralFunctions().switchSceneModality("AddEmployee.fxml");
    }

    @FXML
    void refreshbtn(ActionEvent event) {
        keyword.setText("");
        loadData();
    }

    @FXML
    void sortbtn(ActionEvent event) {
        employeeslist.sort(Comparator.comparing(Employee::getlowerfirstname, Comparator.naturalOrder()));
        employeesView.setItems(employeeslist);
    }

    @FXML
    void sortbtn1(ActionEvent event) {

        employeeslist.sort(Comparator.comparing(Employee::getId, Comparator.naturalOrder()));
        employeesView.setItems(employeeslist);
    }
    public static void view() throws IOException {
        new GeneralFunctions().switchSceneModality("employeesDetailCard.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{

            pagination.setPageFactory(this::createPage);
            loadData();

        /*------Searching With Keryword Logic----------*/
        FilteredList<Employee> filteredList = new FilteredList<>(employeeslist, b -> true);
        keyword.textProperty().addListener((observable,oldvalue,newvalue) ->
        {
            filteredList.setPredicate(employee -> {
                if(newvalue.isEmpty() || newvalue.isBlank() || newvalue==null)
                {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                if(employee.getFirstName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if (employee.getLastName().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(employee.getPhoneNumber().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(employee.getNicNumber().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else if(String.valueOf(employee.getId()).indexOf(searchkeyword) > -1)
                {
                    return true;
                }
                else if(String.valueOf(employee.getSalary()).indexOf(searchkeyword) > -1)
                {
                    return true;
                }
                else if(employee.getDesignation().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                }
                else if(String.valueOf(employee.getJoiningDate()).toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                }
                else if (employee.getEmail().toLowerCase().indexOf(searchkeyword) > -1)
                {
                    return true;
                } else
                {
                    return false;
                }

            });
            SortedList<Employee> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(employeesView.comparatorProperty());
            employeesView.setItems(sortedList);
        });}
              catch (Exception e){
            System.out.println(e);
        }

    }

    private Node createPage(int pageIndex) {
        if(employeeslist.size()>0 && employeeslist.size()<=10) {
            pagination.setPageCount(1);
        } else if(employeeslist.size()>10 && employeeslist.size()<=20)
        {
            pagination.setPageCount(2);
        } else if(employeeslist.size()>20 && employeeslist.size()<=30)
        {
            pagination.setPageCount(3);
        } else if(employeeslist.size()>30 && employeeslist.size()<=40)
        {
            pagination.setPageCount(4);
        } else if(employeeslist.size()>40 && employeeslist.size()<=50)
        {
            pagination.setPageCount(5);
        } else if(employeeslist.size()>50 && employeeslist.size()<=60)
        {
            pagination.setPageCount(6);
        } else if(employeeslist.size()>60 && employeeslist.size()<=70)
        {
            pagination.setPageCount(7);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, employeeslist.size());
        try{
            employeesView.setItems(FXCollections.observableList(employeeslist.subList(fromIndex, toIndex)));
        }catch (Exception e){
            System.out.println("Not Enough Entries");
        }
        return employeesView;
    }

    private void loadData() {
        showrecords();

        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        action.setCellValueFactory(new PropertyValueFactory<>("actionbtn"));
        Designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        SelectedDate.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));
    }

    private void showrecords() {
        employeeslist.clear();
        try {
            resultSet = DatabaseFunctions.getAllEmployees();


            while (resultSet.next()) {
                employeeslist.add(new Employee(resultSet.getDate("joining_date"), resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("email"),resultSet.getString("phone_number"),resultSet.getString("nic_number"),resultSet.getString("designation"),resultSet.getInt("salary"),resultSet.getInt("id"),new CustomMenuButton("Action",resultSet.getInt("id") , resultSet.getString("first_name")+resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("designation"), resultSet.getDouble("salary"), resultSet.getString("gender"),resultSet.getString("phone_number"))));
                employeesView.setItems(employeeslist);
                 }
        }

        catch (NullPointerException e){
            System.out.println("Connection to Database Cannot Be Established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
