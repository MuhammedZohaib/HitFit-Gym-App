package database;

import all_enums.CustomerOrEmployee;
import model_class.Customer;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseFunctions {

    private static final String dbUrl = "jdbc:mysql://doadmin:AVNS_BEoqT0_xVRrXV9Y5PS8@db-mysql-nyc1-56612-do-user-13046066-0.b.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
    private static final String dbUsername = "doadmin";
    private static final String dbPassword = "AVNS_BEoqT0_xVRrXV9Y5PS8";

    private static Connection dbConnection = null;

    public static boolean makeConnection() {
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println("Error! Could not connect to Db: " + e);
        }
        return true;
    }

    public static boolean saveToDb(Customer customer) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("insert into customers (customer_id, first_name, last_name, email, phone_number, password, username, gender, weight, dob,\n" +
                    "monthly_plan, nic, is_active)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?);");

            queryStatement.setInt(1, customer.getCustomerId());
            queryStatement.setString(2, customer.getFirstName());
            queryStatement.setString(3, customer.getLastName());
            queryStatement.setString(4, customer.getEmail());
            queryStatement.setString(5, customer.getPhoneNumber());
            queryStatement.setString(6, customer.getPassword());
            queryStatement.setString(7, customer.getUserName());
            queryStatement.setString(8, customer.getGender());
            queryStatement.setString(9, customer.getWeight());
            queryStatement.setString(10, customer.getDob());
            queryStatement.setInt(11, customer.getMonthlyPlan());
            queryStatement.setString(12, customer.getNicNumber());
            queryStatement.setBoolean(13, false);
            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
        }

        return true;
    }

    public static boolean getAllFromDb(CustomerOrEmployee choice) {

        ResultSet allDataRs = null;

        ArrayList<Integer> customerIds = new ArrayList<>();
        ArrayList<String> firstNames = new ArrayList<>();
        ArrayList<String> lastNames = new ArrayList<>();
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> phoneNumbers = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> genders = new ArrayList<>();
        ArrayList<String> weights = new ArrayList<>();
        ArrayList<String> dobs = new ArrayList<>();
        ArrayList<Integer> monthlyPlans = new ArrayList<>();
        ArrayList<String> nics = new ArrayList<>();
        ArrayList<String> isActives = new ArrayList<>();


        switch (choice) {
            case CUSTOMER:

                PreparedStatement queryStatement = null;
                Customer savedCustomer = new Customer();
                try {
                    queryStatement = dbConnection.prepareStatement("SELECT * FROM customers;");
                    allDataRs = queryStatement.executeQuery();

                    while (allDataRs.next()) {
                       customerIds.add(allDataRs.getInt(0));
                    }

                } catch (SQLException e) {
                    System.out.println("Error in getting ids: " + e);
                }
                break;

            case EMPLOYEE:
                break;
        }

        return true;
    }

    public static int generateId() {

        ResultSet allIds = null;
        int lastId = 0;
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM customers ORDER BY customer_id DESC LIMIT 1;");
            allIds = queryStatement.executeQuery();
            while (allIds.next()) {
                lastId = allIds.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }
        return lastId + 1;
    }

}
