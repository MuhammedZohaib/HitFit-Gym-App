package database;

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
                    "monthly_plan, nic, is_active, salt)\n" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

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
            queryStatement.setString(14, customer.getPasswordSalt());
            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
        }

        return true;
    }

    public static ArrayList<Customer> getAllCustomers() {

        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;
        Customer savedCustomer = new Customer();
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM customers;");
            allDataRs = queryStatement.executeQuery();

            while (allDataRs.next()) {
                savedCustomer.setCustomerId(allDataRs.getInt(1));
                savedCustomer.setFirstName(allDataRs.getString(2));
                savedCustomer.setLastName(allDataRs.getString(3));
                savedCustomer.setEmail(allDataRs.getString(4));
                savedCustomer.setPhoneNumber(allDataRs.getString(5));
                savedCustomer.setPassword(" ");
                savedCustomer.setUserName(allDataRs.getString(7));
                savedCustomer.setGender(allDataRs.getString(8));
                savedCustomer.setWeight(allDataRs.getString(9));
                savedCustomer.setDob(allDataRs.getString(10));
                savedCustomer.setMonthlyPlan(allDataRs.getInt(11));
                savedCustomer.setNicNumber(allDataRs.getString(12));
                savedCustomer.setActive(allDataRs.getBoolean(13));

                allCustomers.add(savedCustomer);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }

//        for (Customer e: allCustomers) {
//            System.out.println(e.getCustomerId());
//            System.out.println(e.getFirstName());
//            System.out.println(e.getLastName());
//            System.out.println(e.getEmail());
//            System.out.println(e.getPassword());
//            System.out.println(e.getUserName());
//            System.out.println(e.getPhoneNumber());
//            System.out.println(e.getGender());
//            System.out.println(e.getWeight());
//            System.out.println(e.getDob());
//            System.out.println(e.getMonthlyPlan());
//            System.out.println(e.getNicNumber());
//        }
        return allCustomers;
    }

    public static ArrayList<String> getUserPassword(int customerId){

        ArrayList<String> saltPassArray = new ArrayList<>();

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
            queryStatement.setInt(1, customerId);
            ResultSet resultSet = queryStatement.executeQuery();

            while (resultSet.next()){
                saltPassArray.add(resultSet.getString("salt"));
                saltPassArray.add(resultSet.getString("password"));
            }

        } catch (SQLException e){
            System.out.println("Error in retrieving customer: " + e);
        }

        return saltPassArray;

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
