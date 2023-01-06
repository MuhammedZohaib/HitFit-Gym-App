package model_class;

import backend_functions.CustomDate;

public class Expense {

    private String description;
    private int amount;
    private java.sql.Date selectedDate;
    private int id;
    private String month;
    private String year;

    long systemCurrentTime = System.currentTimeMillis();
    private java.sql.Date createdDate;

    public Expense(int id, String description, int amount, java.sql.Date selectedDate) {
        this.description = description;
        this.amount = amount;
        this.createdDate = new java.sql.Date(systemCurrentTime);
        this.id = id;

        CustomDate customDate = new CustomDate(selectedDate);

        this.selectedDate = selectedDate;
        this.month = customDate.getMonthName();
        this.year = customDate.getYear();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public java.sql.Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(java.sql.Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
