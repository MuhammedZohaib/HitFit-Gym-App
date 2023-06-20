package backend_functions;

import com.example.semesterProject_2022.ForgetPassword_Controller;
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

    protected final String keyPublic = ""; //Add your public key here
    protected final String keyPrivate = ""; //Add your private key here
    protected final String sendFromEmail = ""; //Add your email id from which the email will be sent
    protected final String sendFromName = "Hit Fit Gym"; //Senders Name

    private ArrayList<String> allEmails = new ArrayList<>();

    private final ClientOptions options = ClientOptions.builder()
            .apiKey(keyPublic)
            .apiSecretKey(keyPrivate)
            .build();

    public static boolean validateEmail(String email) {

        String key = ""; //Mail jet api key here
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
                        <p>Dear valued member,
                        <br>   
                        <br>   
                           Congratulations! On signing up for the HITFIT gym app! We are excited to have you join our community and help you achieve your fitness goals.
                           <br>
                           <br>
                           As a new member, you will have access to a wide range of equipments including personalized workout plans, personal trainer, track of membership and access to our community of fitness enthusiasts.
                           <br>
                           We encourage you to take advantage of all that the app has to offer and to reach out to us with any questions or concerns. Our team is always here to support you and help you succeed.
                           <br>
                           <br>
                           Thank you for choosing HITFIT. We can't wait to see your progress and help you achieve your fitness dreams.
                           <br>
                           <br>
                           Best regards,<br>
                           HITFIT Team
                           <br>
                           </p>
                        """)
                .subject("HITFIT Signup Confirmation")
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

    public void sendPasswordResetEmail(String sendToEmail){
        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(sendToEmail, "Customer"))
                .from(new SendContact(sendFromEmail, sendFromName))
                .htmlPart("""
                        <br>
                        <h1 style="text-align:center">Password Reset Verification!</h1>
                        <br>
                        <h4>Verify if its you</h4>
                        <br>
                        <p>Dear Customer,<br>
                        <br>
                            We have received a request to reset the password for your HITFIT Gym App account.<br> If you did not initiate this request, please let us know immediately by replying to this email.
                           <br>
                           To reset your password, please enter the provided code in the app
                           <br>
                           <br>
                            """+ForgetPassword_Controller.verificationCode+"""
                           <br>
                           <br>
                           Thank you for choosing HITFIT Gym App for your fitness needs. We hope to see you in the gym soon.
                           </p>
                        """)
                .subject("Password Reset Verification")
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

        int i = 0;

        System.out.println(DatabaseFunctions.customersListCount);
        System.out.println(DatabaseFunctions.employeesListCount);

        for (String e : allEmails) {

            if (e.equals(email)) {
                if (i <= DatabaseFunctions.customersListCount){
                    Password.isCustomerOrEmployee = "customer";
                    System.out.println("Customer logging in");
                    return true;
                } else if (i > DatabaseFunctions.employeesListCount) {
                    Password.isCustomerOrEmployee = "employee";
                    System.out.println("Employee Logging in");
                    System.out.println("Error here");
                }
                return true;
            }
            i++;
        }

        return false;
    }
}
