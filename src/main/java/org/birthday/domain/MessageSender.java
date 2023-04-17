package org.birthday.domain;

public interface MessageSender {
    void sendMessage(String to, String subject, String message);
}

