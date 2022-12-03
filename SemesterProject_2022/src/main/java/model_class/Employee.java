package model_class;

public class Employee extends Person{

    private double salary;
    private String designation;

    public Employee(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, double salary, String designation) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password);
        this.salary = salary;
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
