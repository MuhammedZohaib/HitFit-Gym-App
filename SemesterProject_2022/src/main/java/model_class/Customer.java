package model_class;

import all_enums.TimingSlot;
import com.example.semesterProject_2022.MembersPanel_Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.time.LocalDate;

public class Customer extends Person{

    MembersPanel_Controller btn = new MembersPanel_Controller();

    private String address;
    private String dob;
    private String weight;
    private int monthlyPlan;
    private Package monthlyPackage;
    private int customerId;
    private boolean isActive;
    private String passwordSalt;
    private MenuButton action;
    private MenuItem item1 = new MenuItem("View");
    private MenuItem item2 = new MenuItem("Remove");

    public Customer(String firstName, String lastName, String email, String phoneNumber,String nicNumber, int monthlyPlan) {
        super(firstName, lastName, email, "gender", phoneNumber, "userName", "password", nicNumber);
        this.monthlyPlan = monthlyPlan;
        this.action = new MenuButton("Action");
        action.getItems().addAll(item1,item2);
        item1.setOnAction(event ->
        {
            try {
                new MembersPanel_Controller().view();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Customer( String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String nicNumber, String address, String dob, String weight, int monthlyPlan, int customerId, String passwordSalt) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
        this.address = address;
        this.dob = dob;
        this.weight = weight;
        this.monthlyPlan = monthlyPlan;
        this.customerId = customerId;
        this.passwordSalt = passwordSalt;
    }

    public MenuButton getAction() {
        return action;
    }

    public void setAction(MenuButton action) {
        this.action = action;
    }

    public Package getMonthlyPackage() {
        return monthlyPackage;
    }

    public void setMonthlyPackage(Package monthlyPackage) {
        this.monthlyPackage = monthlyPackage;
    }

    public Customer(){
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(int monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public String toString() {
        return super.toString() +"Customer{" +
                "address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", weight='" + weight + '\'' +
                ", monthlyPlan=" + monthlyPlan +
                '}';
    }

}
