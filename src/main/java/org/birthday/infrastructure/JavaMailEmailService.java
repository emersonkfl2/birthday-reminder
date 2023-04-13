package org.birthday.infrastructure;

import jakarta.mail.*;
import org.birthday.domain.EmailService;

import java.util.Properties;

public class JavaMailEmailService implements EmailService {
    private final String username;
    private final String password;
    private final Properties properties;

    public JavaMailEmailService(String username, String password, Properties properties) {
        this.username = username;
        this.password = password;
        this.properties = properties;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

/*        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }*/
    }
}
