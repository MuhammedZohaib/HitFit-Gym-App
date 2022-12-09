package model_class;

import all_enums.AccessLevel;
import all_enums.EmployeeDesignation;
import all_enums.Gender;

import java.util.Date;

public class Trainer extends Employee {

    public Trainer(String firstName, String lastName, String email, Gender gender, String phoneNumber, String userName, String password, EmployeeDesignation designation, Date joiningDate, AccessLevel accessLevel, double salary) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, EmployeeDesignation.TRAINER, joiningDate, AccessLevel.LEVEL0, salary);
    }
}