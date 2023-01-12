package model_class;

import java.sql.Date;

public class BMI {

    private double BMI;
    private String BMIStatus;
    private String BMIDescription;
    private double Weight;
    private double Height;
    private java.sql.Date RecordedDate;
    private String RecordedMonth;

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
