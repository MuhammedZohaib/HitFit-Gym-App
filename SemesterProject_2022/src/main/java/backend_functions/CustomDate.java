package backend_functions;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CustomDate {


    private static final long systemCurrentTime = System.currentTimeMillis();
    private static final java.sql.Date date = new java.sql.Date(systemCurrentTime);
    private String monthName;
    private String year;
    private java.sql.Date inpDate;

    public CustomDate(Date inpDate) {
        this.inpDate = inpDate;
    }

    public static java.sql.Date getCurrentDate() {
        return date;
    }

    public static String getCurrentMonth() {
        return new SimpleDateFormat("MMMM").format(date);
    }
    public static String getCurrentYear() {
        return new SimpleDateFormat("yyyy").format(date);
    }

    public String getMonthName() {

        monthName = new SimpleDateFormat("MMMM").format(inpDate);
        return monthName;

    }

    public String getYear() {

        year = new SimpleDateFormat("yyyy").format(inpDate);
        return year;

    }

}
