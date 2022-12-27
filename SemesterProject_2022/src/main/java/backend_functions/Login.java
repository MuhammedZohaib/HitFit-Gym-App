package backend_functions;

import java.util.ArrayList;

public class Login {

    private ArrayList<String>[] allUsernamesEmails;
    private String username;
    private String email;
    private String password;
    private String queryOption;

    public boolean checkUsernameEmail(String usrInp) {

        if (usrInp.contains("@")) {
            queryOption = "email";
        } else {
            queryOption = "username";
        }

        switch (queryOption) {
            case "email" -> {
                boolean tmp = Email.checkEmail(usrInp);
                if (tmp) {
                    System.out.println("Email already exists");
                    return false;
                } else {
                    email = usrInp;
                }
            }
            case "username" -> {
                boolean tmp1 = Username.checkUsername(usrInp);
                if (tmp1) {
                    System.out.println("Username already exists");
                    return false;
                } else {
                    email = usrInp;
                }
            }
        }
        return false;
    }

}
