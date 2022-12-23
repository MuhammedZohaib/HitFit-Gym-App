package database;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordSaving {

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

    public static boolean verifyPassword(int customerId, String enteredPassword) {

        String[] userSaltPassword = new String[2];
        int i = 0;

        for (String s : DatabaseFunctions.getUserPassword(customerId)) {
            userSaltPassword[i] = s;
            i++;
        }

        enteredPassword = enteredPassword + userSaltPassword[0];

        if (enteredPassword.equals(userSaltPassword[1])){
            System.out.println("Access granted.");
            return true;
        } else {
            System.out.println("wrong password");
            return false;
        }

    }
}
