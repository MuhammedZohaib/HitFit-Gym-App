package backend_functions;

import database.DatabaseFunctions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Username {

    public static boolean checkUsername(String username) {

        ArrayList<String> allUsernames = DatabaseFunctions.getAllUsernames();

        assert allUsernames != null;
        for (String s : allUsernames) {

            if (s.equals(username)) {
                System.out.println("Username found!");
                return true;
            }

        }
        return false;
    }
    
}
