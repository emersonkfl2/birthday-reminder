package org.birthday.application;

import org.birthday.domain.Friend;
import org.birthday.domain.MessageSender;

public class BirthdayMessageSender {

    private final MessageSender emailSender;
    private final MessageSender smsSender;
    private final MessageComposer messageComposer;

    public BirthdayMessageSender(MessageSender emailSender, MessageSender smsSender, MessageComposer messageComposer) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
        this.messageComposer = messageComposer;
    }

    public void sendBirthdayMessage(Friend friend) {
        String subject = messageComposer.composeBirthdaySubject();
        String message = messageComposer.composeBirthdayMessage(friend.getFirstName());
        emailSender.sendMessage(friend.getEmail(), subject, message);
        smsSender.sendMessage(friend.getPhoneNumber(), subject, message);
    }

    public void sendBirthdayReminder(Friend friend, Friend birthdayFriend) {
        String subject = messageComposer.composeReminderSubject();
        String message = messageComposer.composeReminderMessage(friend.getFirstName(), birthdayFriend.getFirstName(), birthdayFriend.getLastName());
        emailSender.sendMessage(friend.getEmail(), subject, message);
        smsSender.sendMessage(friend.getPhoneNumber(), subject, message);
    }
}
