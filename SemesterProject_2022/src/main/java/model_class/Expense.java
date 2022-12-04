package model_class;

import java.util.Date;

public class Expense {

    private String title;
    private double amount;
    private Date date;
    private String specialNotes;

    public Expense(String title, double amount, Date date, String specialNotes) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.specialNotes = specialNotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }
}
