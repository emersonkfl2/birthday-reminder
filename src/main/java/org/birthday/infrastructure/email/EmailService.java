package org.birthday.infrastructure.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.birthday.domain.MessageSender;

import java.util.Properties;

public class EmailService implements MessageSender {

    private final String fromEmail;
    private final String fromEmailPassword;

    public EmailService(String fromEmail, String fromEmailPassword) {
        this.fromEmail = fromEmail;
        this.fromEmailPassword = fromEmailPassword;
    }

    public void sendMessage(String toEmail, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromEmailPassword);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
