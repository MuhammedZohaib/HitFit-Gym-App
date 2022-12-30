package model_class;

import java.util.Date;

public class Employee extends Person {

    private String designation;
    private java.sql.Date joiningDate;
    //    private AccessLevel accessLevel;
    private int salary;
    private int id;
    private String salt;
    private int access;

    public Employee(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String nicNumber, String designation, java.sql.Date joiningDate, int salary, int id, String salt, int access) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
        this.designation = designation;
        this.joiningDate = joiningDate;
        this.salary = salary;
        this.id = id;
        this.salt = salt;
        this.access = access;
    }

    public Employee() {
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
