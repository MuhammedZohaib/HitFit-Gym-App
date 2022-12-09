package database;

import model_class.Customer;

import java.sql.*;

public class DatabaseFunctions {

    private static final String dbUrl = "";
    private static final String dbUsername = "";
    private static final String dbPassword = "";

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
            queryStatement = dbConnection.prepareStatement("insert into customers (first_name, last_name, email, phone_number, password, username, gender, weight, dob,\n" +
                    "                       weight_gain_loose, time_slot, membership_package)\n" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            queryStatement.setString(1, customer.getFirstName());
            queryStatement.setString(2, customer.getLastName());
            queryStatement.setString(3, customer.getEmail());
            queryStatement.setString(4, customer.getPassword());
            queryStatement.setString(5, customer.getUserName());
            queryStatement.setString(6, customer.getGender());
            queryStatement.setDouble(7, customer.getWeight());
            queryStatement.setDate(8, (Date) customer.getDob());
            queryStatement.setString(9, customer.getWeightGainLoose().toString());
            queryStatement.setString(10, customer.getWeightGainLoose().toString());
            queryStatement.setString(11, customer.getSlot());
            queryStatement.setString(12, customer.getMembershipType());

        } catch (SQLException e){
            System.out.println("Error! Could not run query: " + e);
        }

        return true;
    }

}
