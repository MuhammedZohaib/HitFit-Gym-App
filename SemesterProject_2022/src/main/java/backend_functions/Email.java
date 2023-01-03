package backend_functions;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import database.DatabaseFunctions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Email {

    protected final String keyPublic = "007a3211a19074225dabac679933c055";
    protected final String keyPrivate = "eba382894a93bb2779ce07b3cd390445";
    protected final String sendFromEmail = "hitfitgymapp@gmail.com";
    protected final String sendFromName = "Hit Fit Gym";

    private ArrayList<String> allEmails = new ArrayList<>();

    private final ClientOptions options = ClientOptions.builder()
            .apiKey(keyPublic)
            .apiSecretKey(keyPrivate)
            .build();

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

    public void sendWelcomeEmail(String sendToEmail, String sendToName) {

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(sendToEmail, sendToName))
                .from(new SendContact(sendFromEmail, sendFromName))
                .htmlPart("""
                        <br>
                        <h1 style="text-align:center">Sign Up Successful!</h1>
                        <br>
                        <h4>Your account has been created successfully</h4>
                        <br>
                        <p>Dear user, welcome to Hit Fit. Thank you for signing up on our app.</p>
                        <p>Your fitness journey begins here</p>
                        """)
                .subject("Account Sign Up Confirmation")
                .trackOpens(TrackOpens.ENABLED)
                .header("test-header-key", "test-value")
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            System.out.println("Error in sending email: " + e);
        }

    }

    public static boolean checkEmail(String email) {

        ArrayList<String> allEmails = DatabaseFunctions.getAllEmails();

        for (String e : allEmails) {

            if (e.equals(email)) {
                return true;
            }

        }

        return false;
    }
}
