package org.birthday.infrastructure.sms;

import org.birthday.domain.MessageSender;

public class SmsService implements MessageSender {

    public void sendMessage(String toPhoneNumber, String subject, String message) {
        System.out.println("Number: " + toPhoneNumber);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
