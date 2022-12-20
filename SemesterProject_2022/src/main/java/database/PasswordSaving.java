package database;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordSaving {

    public static String makeFinalPassword(String password){

        String changedPassword = DigestUtils.sha3_256Hex(password);
        SecureRandom secureRandom = null;

        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e){
            System.out.println("Error! " + e);
        }

        assert secureRandom != null;
        int tempRandom1 = secureRandom.nextInt(100000,999999);
        String random1 = Integer.toString(tempRandom1);
        changedPassword = changedPassword + random1;

        System.out.println(tempRandom1);

        System.out.println(changedPassword);
        return changedPassword;
    }

//    public static String getPassword(String originalPassword){
//
//    }
}
