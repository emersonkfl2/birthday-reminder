package org.birthday.service;

public class SmsService {

    public void sendSms(String friendName, String phoneNumber, String subject, String message) {
        System.out.println("Sending SMS to: " + friendName);
        System.out.println("Number: " + phoneNumber);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
