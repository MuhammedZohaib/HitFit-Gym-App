package database;

import backend_functions.Login;
import model_class.*;

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
            queryStatement = dbConnection.prepareStatement("""
                    insert into customers (customer_id, first_name, last_name, email, phone_number, password, username, gender, weight, dob,
                    monthly_plan, nic, is_active, salt)
                    values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);""");

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
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }

    }

    public static boolean saveToDb(Transaction transaction) {
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO transactions (transaction_id, created_date, amount, transaction_number, bank_name, account_owner_name, fk_customer_id, status)
                    VALUE (?,?,?,?,?,?,?,?);""");

            queryStatement.setInt(1, transaction.getTransactionId());
            queryStatement.setDate(2, transaction.getCreatedDate());
            queryStatement.setInt(3, transaction.getAmount());
            queryStatement.setString(4, transaction.getTransactionNumber());
            queryStatement.setString(5, transaction.getBankName());
            queryStatement.setString(6, transaction.getAccountOwnerName());
            queryStatement.setInt(7, transaction.getFkCustomerId());
            queryStatement.setBoolean(8, transaction.isStatus());

            queryStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }
    }

    public static boolean saveToDb(Employee employee) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO employees (id, first_name, last_name, designation, nic_number, salary, gender, phone_number, joining_date, username, password, salt, access)
                    VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?);""");

            queryStatement.setInt(1, employee.getId());
            queryStatement.setString(2, employee.getFirstName());
            queryStatement.setString(3, employee.getLastName());
            queryStatement.setString(4, employee.getDesignation());
            queryStatement.setString(5, employee.getNicNumber());
            queryStatement.setInt(6, employee.getSalary());
            queryStatement.setString(7, employee.getGender());
            queryStatement.setString(8, employee.getPhoneNumber());
            queryStatement.setDate(9, employee.getJoiningDate());
            queryStatement.setString(10, employee.getUserName());
            queryStatement.setString(11, employee.getPassword());
            queryStatement.setString(12, employee.getSalt());
            queryStatement.setInt(13, employee.getAccess());

            queryStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }

    }

    public static boolean updateTransactionStatus(int transactionId) {

        PreparedStatement queryStatement = null;
        PreparedStatement queryStatement2 = null;
        int fkCustomerId = 0;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    UPDATE transactions
                    SET status = true
                    WHERE transaction_id = ?;""");
            queryStatement.setInt(1, transactionId);

            queryStatement.executeUpdate();

            try {
                PreparedStatement queryStatement3 = dbConnection.prepareStatement("SELECT fk_customer_id FROM transactions WHERE transaction_id = ?");
                queryStatement3.setInt(1, transactionId);
                ResultSet resultSet = queryStatement3.executeQuery();

                while (resultSet.next()) {
                    fkCustomerId = resultSet.getInt("fk_customer_id");
                }

            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }

            queryStatement2 = dbConnection.prepareStatement("""
                    UPDATE customers
                    SET is_active = true
                    WHERE customer_id = ?;""");

            queryStatement2.setInt(1, fkCustomerId);
            queryStatement2.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }
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

    public static ArrayList<Employee> getAllEmployees() {
        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;
        Employee savedEmployee = new Employee();
        ArrayList<Employee> allEmployees = new ArrayList<>();

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM employees;");
            allDataRs = queryStatement.executeQuery();

            while (allDataRs.next()) {
                savedEmployee.setId(allDataRs.getInt("id"));
                savedEmployee.setFirstName(allDataRs.getString("first_name"));
                savedEmployee.setLastName(allDataRs.getString("last_name"));
                savedEmployee.setDesignation(allDataRs.getString("designation"));
                savedEmployee.setNicNumber(allDataRs.getString("nic_number"));
                savedEmployee.setSalary(allDataRs.getInt("salary"));
                savedEmployee.setGender(allDataRs.getString("gender"));
                savedEmployee.setPhoneNumber(allDataRs.getString("phone_number"));
                savedEmployee.setJoiningDate(allDataRs.getDate("joining_date"));
                savedEmployee.setUserName(allDataRs.getString("username"));
                savedEmployee.setAccess(allDataRs.getInt("access"));
                savedEmployee.setEmail("email");

                allEmployees.add(savedEmployee);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }

//        for (Employee e : allEmployees) {
//            System.out.println(e.getId());
//            System.out.println(e.getFirstName());
//            System.out.println(e.getLastName());
//            System.out.println(e.getEmail());
//            System.out.println(e.getUserName());
//            System.out.println(e.getPhoneNumber());
//            System.out.println(e.getGender());
//            System.out.println(e.getSalary());
//            System.out.println(e.getNicNumber());
//            System.out.println(e.getJoiningDate());
//            System.out.println(e.getDesignation());
//            System.out.println(e.getAccess());
//        }

        return allEmployees;
    }

    public static ArrayList<String> getUserPassword(String customerUsernameEmail) {

        ArrayList<String> saltPassArray = new ArrayList<>();

        switch (Login.queryOption) {
            case "username" -> {
                try {
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM customers WHERE username = ?");
                    queryStatement.setString(1, customerUsernameEmail);
                    ResultSet resultSet = queryStatement.executeQuery();

                    while (resultSet.next()) {
                        saltPassArray.add(resultSet.getString("salt"));
                        saltPassArray.add(resultSet.getString("password"));
                    }

                } catch (SQLException e) {
                    System.out.println("Error in retrieving customer: " + e);
                }
            }

            case "email" -> {
                try {
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM customers WHERE email = ?");
                    queryStatement.setString(1, customerUsernameEmail);
                    ResultSet resultSet = queryStatement.executeQuery();

                    while (resultSet.next()) {
                        saltPassArray.add(resultSet.getString("salt"));
                        saltPassArray.add(resultSet.getString("password"));
                    }


                } catch (SQLException e) {
                    System.out.println("Error in retrieving customer: " + e);
                }
            }
        }

        return saltPassArray;

    }

    public static ArrayList<String> getAllUsernames() {

        ResultSet allUsernamesRs = null;
        PreparedStatement queryStatement = null;
        ArrayList<String> allUsernames = new ArrayList<>();

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT username FROM customers;""");

            allUsernamesRs = queryStatement.executeQuery();

            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
            }

            return allUsernames;

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        return null;
    }

    public static ArrayList<String> getAllEmails() {

        ResultSet allEmailsRs = null;
        PreparedStatement queryStatement = null;
        ArrayList<String> allEmails = new ArrayList<>();

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT email FROM customers;""");

            allEmailsRs = queryStatement.executeQuery();

            while (allEmailsRs.next()) {
                allEmails.add(allEmailsRs.getString(1));
            }

            return allEmails;

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        return null;
    }

    public static ArrayList<String>[] getUsernamesEmails() {

        ArrayList<String>[] allUsernamePasswords = new ArrayList[2];
        ArrayList<String> allUsers = getAllUsernames();
        ArrayList<String> allEmails = getAllEmails();

        allUsernamePasswords[0] = allUsers;
        allUsernamePasswords[1] = allEmails;

        return allUsernamePasswords;
    }

    public static int generateId(String choice) {

        ResultSet allIds = null;
        int lastId = 0;
        PreparedStatement queryStatement = null;

        switch (choice) {
            case "customer" -> {
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
            case "transaction" -> {
                try {
                    queryStatement = dbConnection.prepareStatement("SELECT * FROM transactions ORDER BY transaction_id DESC LIMIT 1;");
                    allIds = queryStatement.executeQuery();
                    while (allIds.next()) {
                        lastId = allIds.getInt(1);
                    }
                } catch (SQLException e) {
                    System.out.println("Error in getting ids: " + e);
                }
                return lastId + 1;
            }
            case "employee" -> {
                try {
                    queryStatement = dbConnection.prepareStatement("SELECT * FROM employees ORDER BY id DESC LIMIT 1;");
                    allIds = queryStatement.executeQuery();
                    while (allIds.next()) {
                        lastId = allIds.getInt(1);
                    }
                } catch (SQLException e) {
                    System.out.println("Error in getting ids: " + e);
                }
                return lastId + 1;
            }
            default -> {
                return 0;
            }
        }
    }

}
