package model_class;

public class Stock {

    private String title;
    private int totalNumber;
    private String specialNotes;

    public Stock(String title, int totalNumber, String specialNotes) {
        this.title = title;
        this.totalNumber = totalNumber;
        this.specialNotes = specialNotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }
}
