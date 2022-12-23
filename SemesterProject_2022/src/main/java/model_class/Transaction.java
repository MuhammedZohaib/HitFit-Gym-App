package model_class;

import java.sql.Date;

public class Transaction {

    private int transactionId;
    private Date createdDate;
    private int amount;
    private String transactionNumber;
    private String bankName;
    private String accountOwnerName;
    private int fkCustomerId;

    public Transaction(int transactionId, Date createdDate, int amount, String transactionNumber, String bankName, String accountOwnerName) {
        this.transactionId = transactionId;
        this.createdDate = createdDate;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.bankName = bankName;
        this.accountOwnerName = accountOwnerName;
    }

    public Transaction() {
    }

    public Transaction(int transactionId, Date createdDate, int amount, String transactionNumber, String bankName, String accountOwnerName, int fkCustomerId) {
        this.transactionId = transactionId;
        this.createdDate = createdDate;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.bankName = bankName;
        this.accountOwnerName = accountOwnerName;
        this.fkCustomerId = fkCustomerId;
    }

    public int getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(int fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }
}
