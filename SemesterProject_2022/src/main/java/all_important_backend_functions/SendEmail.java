package all_important_backend_functions;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;

public class SendEmail {

    protected final String keyPublic = "007a3211a19074225dabac679933c055";
    protected final String keyPrivate = "eba382894a93bb2779ce07b3cd390445";
    protected final String sendFromEmail = "hitfitgymapp@gmail.com";
    protected final String sendFromName = "Hit Fit Gym";

    private final ClientOptions options = ClientOptions.builder()
            .apiKey(keyPublic)
            .apiSecretKey(keyPrivate)
            .build();

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
}
