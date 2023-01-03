package backend_functions;

import database.DatabaseFunctions;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {


    public static String isCustomerOrEmployee;

    private static void checkCustomerEmployee(){

    }

    public static String[] makeFinalPassword(String password) {

        String changedPassword = DigestUtils.sha3_256Hex(password);
        SecureRandom secureRandom = null;
        String[] passSalt = new String[2];

        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error! " + e);
        }

        assert secureRandom != null;
        int tempRandom1 = secureRandom.nextInt(100000, 999999);
        String random1 = Integer.toString(tempRandom1);
        changedPassword = changedPassword + random1;

        passSalt[0] = random1;
        passSalt[1] = changedPassword;

        return passSalt;
    }

    public static boolean verifyPassword(String customerUsernameEmail, String enteredPassword) {

        try {

            String[] userSaltPassword = new String[2];
            int i = 0;

            if (isCustomerOrEmployee.equals("customer")){
                for (String s : DatabaseFunctions.getUserPassword(customerUsernameEmail)) {
                    userSaltPassword[i] = s;
                    i++;
                }
            } else if (isCustomerOrEmployee.equals("employee")) {
                for (String s : DatabaseFunctions.getEmployeePassword(customerUsernameEmail)) {
                    userSaltPassword[i] = s;
                    i++;
                }
            }

            String changedPassword = DigestUtils.sha3_256Hex(enteredPassword);

            changedPassword = changedPassword + userSaltPassword[0];

            if (changedPassword.equals(userSaltPassword[1])) {
                System.out.println("Access granted.");
                return true;
            } else {
                System.out.println("wrong password");
                return false;
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
