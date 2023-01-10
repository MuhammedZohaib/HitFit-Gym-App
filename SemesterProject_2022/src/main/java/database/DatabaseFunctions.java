package database;

import backend_functions.CustomDate;
import backend_functions.Login;
import model_class.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseFunctions {

    private static final String dbUrl = "jdbc:mysql://doadmin:AVNS_BEoqT0_xVRrXV9Y5PS8@db-mysql-nyc1-56612-do-user-13046066-0.b.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
    private static final String dbUsername = "doadmin";
    private static final String dbPassword = "AVNS_BEoqT0_xVRrXV9Y5PS8";

    private static Connection dbConnection = null;

    public static int customersListCount;
    public static int employeesListCount;
    public static int totalList;

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
                    insert into customers (id, first_name, last_name, email, phone_number, password, username, gender, weight, dob,
                    monthly_plan, nic, is_active, salt, address)
                    values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);""");

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
            queryStatement.setString(15, customer.getAddress());
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
                    INSERT INTO transactions (id, created_date, amount, transaction_number, bank_name, account_owner_name, fk_customer_id, status)
                    VALUE (?,?,?,?,?,?,?,?);""");

            queryStatement.setInt(1, transaction.getTransactionId());
            queryStatement.setDate(2, CustomDate.getCurrentDate());
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
                    INSERT INTO employees (id, first_name, last_name, designation, nic_number, salary, gender, phone_number, joining_date, username, password, salt, access,email)
                    VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?);""");

            queryStatement.setInt(1, employee.getId());
            queryStatement.setString(2, employee.getFirstName());
            queryStatement.setString(3, employee.getLastName());
            queryStatement.setString(4, employee.getDesignation());
            queryStatement.setString(5, employee.getNicNumber());
            queryStatement.setInt(6, employee.getSalary());
            queryStatement.setString(7, employee.getGender());
            queryStatement.setString(8, employee.getPhoneNumber());
            queryStatement.setDate(9, CustomDate.getCurrentDate());
            queryStatement.setString(10, employee.getUserName());
            queryStatement.setString(11, employee.getPassword());
            queryStatement.setString(12, employee.getSalt());
            queryStatement.setInt(13, employee.getAccess());
            queryStatement.setString(14, employee.getEmail());

            queryStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }

    }

    public static boolean saveToDb(Expense expense, Integer fkEmployeeId) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO expenses (id, description,created_date, amount, month, year, fk_employee_id, selected_date)
                    VALUE (?,?,?,?,?,?,?,?)
                    """);

            queryStatement.setInt(1, expense.getId());
            queryStatement.setString(2, expense.getDescription());
            queryStatement.setDate(3, CustomDate.getCurrentDate());
            queryStatement.setInt(4, expense.getAmount());
            queryStatement.setString(5, expense.getMonth());
            queryStatement.setString(6, expense.getYear());
            queryStatement.setObject(7, fkEmployeeId);
            queryStatement.setDate(8, expense.getSelectedDate());

            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return true;
    }

    public static void updateCustomerPassword(String email, String[] password) {
        PreparedStatement queryStatement = null;
        try {
            queryStatement = dbConnection.prepareStatement("UPDATE customers SET password = ?, salt = ? WHERE email = ?");
            queryStatement.setString(1, password[1]);
            queryStatement.setString(2, password[0]);
            queryStatement.setString(3, email);
            queryStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public static void updateEmployeePassword(String email, String[] password) {
        PreparedStatement queryStatement = null;
        try {
            queryStatement = dbConnection.prepareStatement("UPDATE employees SET password = ?, salt = ? WHERE email = ?");
            queryStatement.setString(1, password[1]);
            queryStatement.setString(2, password[0]);
            queryStatement.setString(3, email);
            queryStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
                    WHERE id = ?;""");
            queryStatement.setInt(1, transactionId);

            queryStatement.executeUpdate();

            try {
                PreparedStatement queryStatement3 = dbConnection.prepareStatement("SELECT fk_customer_id FROM transactions WHERE id = ?");
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
                    WHERE id = ?;""");

            queryStatement2.setInt(1, fkCustomerId);
            queryStatement2.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }
    }

    public static ResultSet getAllCustomers() {

        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT id, first_name, last_name, email, phone_number, username, gender, weight, dob, monthly_plan, nic, is_active, address FROM customers
                    WHERE current_status = true;
                    """);
            allDataRs = queryStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }

        return allDataRs;
    }

    public static ResultSet getAllEmployees() {
        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT id, first_name, last_name, designation, nic_number, salary, gender, phone_number, joining_date, username, access, email FROM employees
                    WHERE current_status = true;""");
            allDataRs = queryStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }
        return allDataRs;
    }

    public static ResultSet getAllExpenses() {

        PreparedStatement queryStatement = null;
        ResultSet expensesRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT id, description, amount, selected_date, month, year FROM expenses
                    WHERE current_status = true;""");
            expensesRs = queryStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return expensesRs;
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

    public static ArrayList<String> getEmployeePassword(String employeeUsernameEmail) {

        ArrayList<String> saltPassArray = new ArrayList<>();

        switch (Login.queryOption) {
            case "username" -> {
                try {
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM employees WHERE username = ?");
                    queryStatement.setString(1, employeeUsernameEmail);
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
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM employees WHERE email = ?");
                    queryStatement.setString(1, employeeUsernameEmail);
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
                    SELECT username FROM customers
                    WHERE current_status = true;""");

            allUsernamesRs = queryStatement.executeQuery();

            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
                customersListCount++;
            }

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT username FROM employees
                    WHERE current_status = true;""");

            allUsernamesRs = queryStatement.executeQuery();

            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
                employeesListCount++;
            }

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        return allUsernames;

    }

    public static ArrayList<String> getAllEmails() {

        ResultSet allEmailsRs = null;
        PreparedStatement queryStatement = null;
        ArrayList<String> allEmails = new ArrayList<>();


        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT email FROM customers
                    WHERE current_status = true;""");

            allEmailsRs = queryStatement.executeQuery();

            while (allEmailsRs.next()) {
                allEmails.add(allEmailsRs.getString(1));
                customersListCount++;
            }


        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT email FROM employees
                    WHERE current_status = true;""");

            allEmailsRs = queryStatement.executeQuery();

            while (allEmailsRs.next()) {
                allEmails.add(allEmailsRs.getString(1));
                employeesListCount++;
            }


        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }


        return allEmails;
    }

    public static int getNumberOfCustomers() {

        PreparedStatement queryStatement = null;
        int allCustomers = 0;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT COUNT(id)
                    FROM customers
                    WHERE current_status = true;""");

            ResultSet customersRs = queryStatement.executeQuery();

            while (customersRs.next()) {
                allCustomers = customersRs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting number of customers: " + e);
        }

        return allCustomers;
    }

    public static int getTotalList() {

        totalList = customersListCount + employeesListCount;
        return totalList;

    }

    public static boolean deleteData(String tableName, int id) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    CALL delete_data(?,?);""");

            queryStatement.setString(1, tableName);
            queryStatement.setInt(2, id);

            queryStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("error in deleting: " + e);
        }

        return false;
    }

    public static int generateId(String choice) {

        ResultSet allIds = null;
        int lastId = 0;
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    CALL get_ids(?)
                    """);
            queryStatement.setString(1, choice);
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
