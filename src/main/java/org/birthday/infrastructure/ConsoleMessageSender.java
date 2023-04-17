package org.birthday.infrastructure;

import org.birthday.domain.MessageSender;

public class ConsoleMessageSender implements MessageSender {
    @Override
    public void sendMessage(String recipient, String subject, String message) {
        System.out.println("Recipient: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("---------------------------------------------------");
    }
}
