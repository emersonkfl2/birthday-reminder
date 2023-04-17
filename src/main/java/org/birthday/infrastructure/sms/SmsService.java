package org.birthday.infrastructure.sms;

public class SmsService {

    public void sendMessage(String toPhoneNumber, String subject, String message) {
        System.out.println("Number: " + toPhoneNumber);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
