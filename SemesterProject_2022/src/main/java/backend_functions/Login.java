package backend_functions;

import java.util.ArrayList;

public class Login {

    private ArrayList<String>[] allUsernamesEmails;
    private String password;
    private String emailUsername;
    private boolean logInSuccessful;
    public static String queryOption;

    public boolean checkUsernameEmail(String choice) {

        if (emailUsername.contains("@")) {
            queryOption = "email";
        } else {
            queryOption = "username";
        }

        switch (queryOption) {
            case "email" -> {
                boolean tmp = Email.checkEmail(emailUsername);
                if (tmp) {
                    System.out.println("Email already exists");
                    return false;
                } else {
                    return true;
                }
            }
            case "username" -> {
                boolean tmp1 = Username.checkUsername(emailUsername);
                if (tmp1) {
                    System.out.println("Username already exists");
                    return false;
                } else {
                    return true;
                }
            }
        }

        return true;
    }

    public void checkPassword() {

        if (Password.verifyPassword(emailUsername, password)) {
            System.out.println("Password matched");
            logInSuccessful = true;
        }

    }

    public boolean userLoggedInStatus() {

        checkPassword();

        if (logInSuccessful) {
            System.out.println("User logged in successfully");
            return true;
        } else {
            return false;
        }

    }

    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    public String getEmailUsername() {
        return emailUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
