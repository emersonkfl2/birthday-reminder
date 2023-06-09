package org.birthday.application;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class BirthdayMessageScheduler {

    private final FriendRepository friendRepository;
    private final BirthdayMessageSender birthdayMessageSender;
    private final Clock clock;

    public BirthdayMessageScheduler(FriendRepository friendRepository, BirthdayMessageSender birthdayMessageSender, Clock clock) {
        this.friendRepository = friendRepository;
        this.birthdayMessageSender = birthdayMessageSender;
        this.clock = clock;
    }

    public void run() {
        LocalDate today = LocalDate.now(clock);
        List<Friend> friends = friendRepository.findAll();

        Friend birthdayFriend = findFriendWithBirthdayToday(friends, today);

        for (Friend friend : friends) {
            if (isBirthday(today, friend.getDateOfBirth())) {
                birthdayMessageSender.sendBirthdayMessage(friend);
            } else if (birthdayFriend != null) {
                birthdayMessageSender.sendBirthdayReminder(friend, birthdayFriend);
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

    private Friend findFriendWithBirthdayToday(List<Friend> friends, LocalDate today) {
        return friends.stream()
                .filter(friend -> isBirthday(today, friend.getDateOfBirth()))
                .findFirst()
                .orElse(null);
    }
}
