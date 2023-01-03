package model_class;

import java.util.Date;

public class Expense {

    private String title;
    private int amount;
    private Date selectedDate;

    long systemCurrentTime = System.currentTimeMillis();
    private java.sql.Date createdDate;

    public Expense(String title, int amount) {
        this.title = title;
        this.amount = amount;
        this.createdDate = new java.sql.Date(systemCurrentTime);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
