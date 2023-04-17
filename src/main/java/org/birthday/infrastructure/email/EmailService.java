package org.birthday.infrastructure.email;

import org.birthday.domain.MessageSender;

public class EmailService implements MessageSender {

    public void sendMessage(String toEmail, String subject, String message) {
        System.out.println("Email: " + toEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
