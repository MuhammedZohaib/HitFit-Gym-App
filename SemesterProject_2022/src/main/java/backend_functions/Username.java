package backend_functions;

import database.DatabaseFunctions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Username {

    public static boolean checkUsername(String username) {

        ResultSet allUsernamesRs = DatabaseFunctions.getAllUsernames();
        ArrayList<String> allUsernames = new ArrayList<>();


        try {
            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
            }
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }

        for (String s : allUsernames) {

            if (s.equals(username)) {
                System.out.println("Username found!");
                return true;
            }

        }
        return false;
    }
    
}
