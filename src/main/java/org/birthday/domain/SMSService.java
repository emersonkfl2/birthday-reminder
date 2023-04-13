package org.birthday.domain;

public interface SMSService {
    void sendSMS(String recipient, String message);
}
