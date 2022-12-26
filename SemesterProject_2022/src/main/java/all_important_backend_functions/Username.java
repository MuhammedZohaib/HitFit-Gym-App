package all_important_backend_functions;

import database.DatabaseFunctions;

public class Username {

    public static boolean checkUsername(String username) {

        DatabaseFunctions.setAllUsernames();

        for (String s : DatabaseFunctions.getAllUsernames()) {

            if (s.equals(username)) {
                System.out.println("Username found!");
                return true;
            }

        }
        return false;
    }
}
