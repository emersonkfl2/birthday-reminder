package org.birthday.domain;

public interface EmailService {
    void sendEmail(String recipient, String email, String message);

    default void sendEmail(String recipient, String email, String message, String subject) {

    }
}

