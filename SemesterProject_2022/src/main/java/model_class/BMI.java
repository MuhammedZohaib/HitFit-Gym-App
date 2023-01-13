package model_class;

import backend_functions.CustomDate;

import java.sql.Date;

public class BMI {

    private double BMI;
    private String BMIStatus;
    private String BMIDescription;
    private double Weight;
    private double Height;
    private java.sql.Date RecordedDate;
    private String RecordedMonth;
    private int id;

    public BMI(double weight, Date recordedDate, int id, double bmi, double height) {
        Weight = weight;
        RecordedDate = recordedDate;
        this.id = id;
        this.Height = height;
        this.BMI = bmi;

        CustomDate customDate = new CustomDate(recordedDate);
        this.RecordedMonth = customDate.getMonthName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getBMIStatus() {
        return BMIStatus;
    }

    public void setBMIStatus(String BMIStatus) {
        this.BMIStatus = BMIStatus;
    }

    public String getBMIDescription() {
        return BMIDescription;
    }

    public void setBMIDescription(String BMIDescription) {
        this.BMIDescription = BMIDescription;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public Date getRecordedDate() {
        return RecordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        RecordedDate = recordedDate;
    }

    public String getRecordedMonth() {
        return RecordedMonth;
    }

    public void setRecordedMonth(String recordedMonth) {
        RecordedMonth = recordedMonth;
    }
}
