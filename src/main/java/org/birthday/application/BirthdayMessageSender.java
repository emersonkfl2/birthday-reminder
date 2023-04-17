package org.birthday.application;

import org.birthday.domain.Friend;
import org.birthday.infrastructure.email.EmailService;
import org.birthday.infrastructure.sms.SmsService;

public class BirthdayMessageSender {

    private final EmailService emailService;
    private final SmsService smsService;
    private final MessageComposer messageComposer;

    public BirthdayMessageSender(EmailService emailService, SmsService smsService, MessageComposer messageComposer) {
        this.emailService = emailService;
        this.smsService = smsService;
        this.messageComposer = messageComposer;
    }

    public void sendBirthdayMessage(Friend friend) {
        String subject = messageComposer.composeBirthdaySubject();
        String message = messageComposer.composeBirthdayMessage(friend.getFirstName());
        emailService.sendMessage(friend.getEmail(), subject, message);
        smsService.sendMessage( friend.getPhoneNumber(), subject, message);
    }

    public void sendBirthdayReminder(Friend friend, Friend birthdayFriend) {
        String subject = messageComposer.composeReminderSubject();
        String message = messageComposer.composeReminderMessage(friend.getFirstName(), birthdayFriend.getFirstName(), birthdayFriend.getLastName());
        emailService.sendMessage(friend.getEmail(), subject, message);
        smsService.sendMessage( friend.getPhoneNumber(), subject, message);
    }
}

