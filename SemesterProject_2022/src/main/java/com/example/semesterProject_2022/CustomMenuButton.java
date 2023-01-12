package com.example.semesterProject_2022;

import javafx.scene.control.MenuButton;

public class CustomMenuButton extends MenuButton {

    private int ButtonId;
    private String FullName,weight,Address,Email,Username,PackageType,PackagePrice,designation,gender,phone;
    private double salary;

    // *FOR TRANSACTIONS

    public CustomMenuButton(String s, int buttonId) {
        super(s);
        ButtonId = buttonId;
    }

    //
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // constructor for employees
    public CustomMenuButton(String s, int buttonId, String fullName, String email, String username, String designation, double salary,String gender,String phone) {
        super(s);
        this.phone = phone;
        this.gender = gender;
        ButtonId = buttonId;
        FullName = fullName;
        Email = email;
        Username = username;
        this.designation = designation;
        this.salary = salary;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public int getButtonId() {
        return ButtonId;
    }

    public void setButtonId(int buttonId) {
        ButtonId = buttonId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPackageType() {
        return PackageType;
    }

    public void setPackageType(String packageType) {
        PackageType = packageType;
    }

    public String getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        PackagePrice = packagePrice;
    }

    // constructor for Members
    public CustomMenuButton(int ButtonId, String s, String fullName, String weight, String address, String email, String username,String packagePrice) {
        super(s);
        FullName = fullName;
        this.ButtonId=ButtonId;
        this.weight = weight;
        Address = address;
        Email = email;
        Username = username;
        PackagePrice = packagePrice;
        if(Integer.parseInt(PackagePrice)==2000)
        {
            PackageType = "Beginner";
        } else if(Integer.parseInt(PackagePrice)==3000)
        {
            PackageType = "Starter";
        } else if(Integer.parseInt(PackagePrice)==4500)
        {
            PackageType = "Pro";
        } else
        {
            PackageType = "nil";
        }
    }
}
