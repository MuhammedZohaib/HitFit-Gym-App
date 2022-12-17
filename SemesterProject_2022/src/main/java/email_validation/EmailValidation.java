package email_validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmailValidation {

    public static boolean validateEmail(String email) {

        String key = "e32208c4e0f145c3bea410b96929c34b";
        String ip = " "; //ip address can be blank
        String targetURL = "https://api.zerobounce.net/v2/validate?api_key=" + key + "&email=" + email + "&ip_address=" + ip;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

            String[] resultString = response.toString().split(",");
            if (resultString[1].contains("\"valid\"")) {
                System.out.println("email is valid");
                return true;
            } else {
                System.out.println("email is invalid");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return true;
    }

}
