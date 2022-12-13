package database;

import all_enums.CustomerOrEmployee;
import model_class.Customer;

import java.sql.*;

public class DatabaseFunctions {

    private static final String dbUrl = "mysql://doadmin:AVNS_BEoqT0_xVRrXV9Y5PS8@db-mysql-nyc1-56612-do-user-13046066-0.b.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
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
            queryStatement = dbConnection.prepareStatement("insert into customers (first_name, last_name, email, phone_number, password, username, gender, weight, dob,\n" +
                    "                       weight_gain_loose, time_slot, membership_package, nic)\n" +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            queryStatement.setString(1, customer.getFirstName());
            queryStatement.setString(2, customer.getLastName());
            queryStatement.setString(3, customer.getEmail());
            queryStatement.setString(4, customer.getPhoneNumber());
            queryStatement.setString(5, customer.getPassword());
            queryStatement.setString(6, customer.getUserName());
            queryStatement.setString(7, customer.getGender());
            queryStatement.setDouble(8, customer.getWeight());
            queryStatement.setString(9, customer.getDob());
            queryStatement.setString(10, customer.getWeightGainLoose());
            queryStatement.setString(11, customer.getSlot());
            queryStatement.setString(12, customer.getMembershipType());
            queryStatement.setString(13, customer.getNicNumber());

        } catch (SQLException e){
            System.out.println("Error! Could not run query: " + e);
        }

        return true;
    }

    public static boolean getAllFromDb(CustomerOrEmployee choice){

        switch (choice){
            case CUSTOMER:
                
                break;

            case EMPLOYEE:
                break;
        }

        return true;
    }
}
