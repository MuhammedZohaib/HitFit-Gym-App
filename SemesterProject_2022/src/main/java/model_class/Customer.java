package model_class;

import all_enums.Gender;
import all_enums.MembershipPlans;
import all_enums.TimingSlot;
import all_enums.WeightGainLoose;

import java.util.Date;

public class Customer extends Person{

    private String address;
    private Date dob;
    private double weight;
    private TimingSlot slot;
    private MembershipPlans membershipType;
    private WeightGainLoose weightGainLoose;

    public Customer(String firstName, String lastName, String email, Gender gender, String phoneNumber, String userName, String password, String address, Date dob, double weight, TimingSlot slot, MembershipPlans membershipType, WeightGainLoose weightGainLoose, String nic) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nic);
        this.address = address;
        this.dob = dob;
        this.weight = weight;
        this.slot = slot;
        this.membershipType = membershipType;
        this.weightGainLoose = weightGainLoose;
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
        return slot.toString();
    }

    public void setSlot(TimingSlot slot) {
        this.slot = slot;
    }

    public String getMembershipType() {
        return membershipType.toString();
    }

    public void setMembershipType(MembershipPlans membershipType) {
        this.membershipType = membershipType;
    }

    public String getWeightGainLoose() {
        return weightGainLoose.toString();
    }

    public void setWeightGainLoose(WeightGainLoose weightGainLoose) {
        this.weightGainLoose = weightGainLoose;
    }
}
