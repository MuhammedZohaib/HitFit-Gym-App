package model_class;

import backend_functions.CustomDate;
import com.example.semesterProject_2022.CustomMenuButton;
import com.example.semesterProject_2022.EmployeesDetailCard_Controller;
import com.example.semesterProject_2022.EmployeesPanel_Controller;
import com.example.semesterProject_2022.MembersPanel_Controller;
import database.DatabaseFunctions;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.Date;

public class Employee extends Person {

    private String designation;
    private java.sql.Date joiningDate;
    private String year;
    private String month;
    private CustomDate customDate;

    //    private AccessLevel accessLevel;
    private int salary;
    private int id;
    private String salt;
    private int access;

    /**/
    private CustomMenuButton actionbtn;
    private MenuItem item1 = new MenuItem("View");
    private MenuItem item2 = new MenuItem("Remove");
    /**/

    public CustomMenuButton getActionbtn() {
        return actionbtn;
    }

    public void setActionbtn(CustomMenuButton actionbtn) {
        this.actionbtn = actionbtn;
    }

    public Employee( java.sql.Date joiningDate,String firstName, String lastName, String email, String phoneNumber, String nicNumber, String designation, int salary, int id, CustomMenuButton actionbtn) {
        super(firstName, lastName, email, "gender", phoneNumber, "Username", "password", nicNumber);
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.id = id;
        this.actionbtn = actionbtn;
        this.actionbtn.setStyle("-fx-background-color: #00C2FF; -fx-background-radius: 12px;");
        this.actionbtn.setTextFill(Paint.valueOf("White"));
        actionbtn.getItems().addAll(item1,item2);
        item1.setOnAction(event ->
        {
            EmployeesDetailCard_Controller.FullName=actionbtn.getFullName();
            EmployeesDetailCard_Controller.Phone=actionbtn.getPhone();
            EmployeesDetailCard_Controller.Gender=actionbtn.getGender();
            EmployeesDetailCard_Controller.Emails=actionbtn.getEmail();
            EmployeesDetailCard_Controller.Username=actionbtn.getUsername();
            EmployeesDetailCard_Controller.Designation=actionbtn.getDesignation();
            EmployeesDetailCard_Controller.Salary="Rs. "+String.valueOf(actionbtn.getSalary());
            try {
                EmployeesPanel_Controller.view();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        item2.setOnAction(event ->
        {

            EmployeesPanel_Controller.deletingId=actionbtn.getButtonId();
            DatabaseFunctions.deleteData("employees", EmployeesPanel_Controller.deletingId);
        });
        customDate = new CustomDate(joiningDate);

        this.month = customDate.getMonthName();
        this.year = customDate.getYear();
    }

    public Employee( java.sql.Date joiningDate,String firstName, String lastName, String email, String phoneNumber, String nicNumber, String designation, int salary, int id, String gender, String username, String password, String salt) {
        super(firstName, lastName, email, gender, phoneNumber, username, password, nicNumber);
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.id = id;
        this.salt = salt;

        customDate = new CustomDate(joiningDate);

        this.month = customDate.getMonthName();
        this.year = customDate.getYear();
    }

//    public Employee(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String nicNumber, String designation, java.sql.Date joiningDate, int salary, int id, String salt, int access) {
//        super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
//        this.designation = designation;
//        this.joiningDate = joiningDate;
//        this.salary = salary;
//        this.id = id;
//        this.salt = salt;
//        this.access = access;
//
//    }

    public Employee() { }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public java.sql.Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = (java.sql.Date) joiningDate;
    }

/*
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
*/
    public String getlowerfirstname()
    {
        return getFirstName().toLowerCase();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJoiningDate(java.sql.Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
