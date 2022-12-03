package model_class;

import java.util.Date;

public class Customer extends Person{

    private String address;
    private Date dob;
    private double weight;
    private String slot;
    private String membershipType;

    public Customer(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String address, Date dob, double weight, String slot, String membershipType) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password);
        this.address = address;
        this.dob = dob;
        this.weight = weight;
        this.slot = slot;
        this.membershipType = membershipType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
}
