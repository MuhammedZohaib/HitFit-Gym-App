package model_class;

import all_enums.Gender;
import all_enums.TimingSlot;

import java.time.LocalDate;

public class Customer extends Person{

    private String address;
    private LocalDate dob;
    private String weight;
    private TimingSlot slot;
    private int monthlyPlan;

    public Customer(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String address, LocalDate dob, String weight, int monthlyPlan, String nic) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nic);
        this.address = address;
        this.dob = dob;
        this.weight = weight;
        this.slot = slot;
        this.monthlyPlan = monthlyPlan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob.toString();
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSlot() {
        return slot.toString();
    }

    public void setSlot(TimingSlot slot) {
        this.slot = slot;
    }

    public int getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthlyPlan(int monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }
}
