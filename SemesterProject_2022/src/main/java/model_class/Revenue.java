package model_class;

public class Revenue {

    private int id;
    private String forMonth;
    private String forYear;
    private int amount;


    public Revenue(int id, String forMonth, String forYear, int amount) {
        this.id = id;
        this.forMonth = forMonth;
        this.forYear = forYear;
        this.amount = amount;
    }

    public Revenue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForMonth() {
        return forMonth;
    }

    public void setForMonth(String forMonth) {
        this.forMonth = forMonth;
    }

    public String getForYear() {
        return forYear;
    }

    public void setForYear(String forYear) {
        this.forYear = forYear;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
