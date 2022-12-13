package model_class;

import all_enums.AccessLevel;
import all_enums.EmployeeDesignation;
import all_enums.Gender;

import java.util.Date;

public class Admin extends Employee {

    public Admin(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, EmployeeDesignation designation, Date joiningDate, AccessLevel accessLevel, double salary, String nicNumber) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, EmployeeDesignation.ADMIN, joiningDate, AccessLevel.LEVEL2, salary, nicNumber);
    }
}
