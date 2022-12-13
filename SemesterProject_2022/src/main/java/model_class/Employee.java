package model_class;

import all_enums.AccessLevel;
import all_enums.EmployeeDesignation;
import all_enums.Gender;

import java.util.Date;

public abstract class Employee extends Person{

    private EmployeeDesignation designation;
    private Date joiningDate;
    private AccessLevel accessLevel;
    private double salary;

    public Employee(String firstName, String lastName, String email, Gender gender, String phoneNumber, String userName, String password, EmployeeDesignation designation, Date joiningDate, AccessLevel accessLevel, double salary, String nicNumber) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.accessLevel = accessLevel;
        this.salary = salary;
    }

    public EmployeeDesignation getDesignation() {
        return designation;
    }

    public void setDesignation(EmployeeDesignation designation) {
        this.designation = designation;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
