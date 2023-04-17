package org.birthday.service;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import java.time.LocalDate;
import java.util.List;

public class BirthdayReminderService {

    private final FriendRepository friendRepository;
    private final EmailService emailService;
    private final SmsService smsService;

    public BirthdayReminderService(FriendRepository friendRepository, EmailService emailService, SmsService smsService) {
        this.friendRepository = friendRepository;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void sendBirthdayMessages() {
        LocalDate today = LocalDate.now();
        List<Friend> friends = friendRepository.findAll();

        for (Friend friend : friends) {
            if (isBirthday(today, friend.getDateOfBirth())) {
                sendBirthdayMessage(friend);
            } else if (isBirthdayOfSomeoneElse(today, friend.getDateOfBirth())) {
                sendBirthdayReminder(friend);
            }
        }
    }

    private boolean isBirthday(LocalDate today, LocalDate dateOfBirth) {
        LocalDate adjustedDateOfBirth = adjustBirthdayForLeapYear(today, dateOfBirth);
        return today.getMonth() == adjustedDateOfBirth.getMonth() && today.getDayOfMonth() == adjustedDateOfBirth.getDayOfMonth();
    }

    private LocalDate adjustBirthdayForLeapYear(LocalDate today, LocalDate dateOfBirth) {
        if (dateOfBirth.isLeapYear() && !today.isLeapYear() && dateOfBirth.getMonth().getValue() == 2 && dateOfBirth.getDayOfMonth() == 29) {
            return dateOfBirth.withDayOfMonth(28);
        }
        return dateOfBirth;
    }

    private boolean isBirthdayOfSomeoneElse(LocalDate today, LocalDate dateOfBirth) {
        return !isBirthday(today, dateOfBirth);
    }

    private void sendBirthdayMessage(Friend friend) {
        String subject = "Happy birthday!";
        String message = "Happy birthday, dear " + friend.getFirstName() + "!";
        emailService.sendEmail(friend.getEmail(), subject, message);
        smsService.sendSms(friend.getFirstName() + friend.getLastName(), friend.getPhoneNumber(), subject, message);
    }

    private void sendBirthdayReminder(Friend friend) {
        String subject = "Birthday Reminder";
        String message = "Dear " + friend.getFirstName() + ",\n" +
                "Today is " + friend.getFirstName() + " " + friend.getLastName() + "'s birthday.\n" +
                "Don't forget to send him a message!";
        emailService.sendEmail(friend.getEmail(), subject, message);
        smsService.sendSms(friend.getFirstName() + friend.getLastName(), friend.getPhoneNumber(), subject, message);
    }
}
