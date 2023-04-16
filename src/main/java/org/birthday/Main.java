package org.birthday;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;
import org.birthday.infrastructure.FlatFileFriendRepository;
import org.birthday.infrastructure.SQLiteFriendRepository;
import org.birthday.service.EmailService;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Test JPA FriendRepository with SQLite
        FriendRepository sqLiteRepository = new SQLiteFriendRepository("birthdayReminderPU");
        List<Friend> friends = sqLiteRepository.findAll();

        System.out.println("\n------------Getting friends from SQLite------------");
        for (Friend friend : friends) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() +
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail());
        }

        //Test FlatFile FriendRepository
        FriendRepository friendFileRepository = new FlatFileFriendRepository(Path.of("friends.txt"));
        List<Friend> friendsFile = friendFileRepository.findAll();

        System.out.println("\n------------Getting friends from file------------");
        for (Friend friend : friendsFile) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() +
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail());
        }


        String fromEmail = "your-email@gmail.com";
        String fromEmailPassword = "your-email-password";
        EmailService emailService = new EmailService(fromEmail, fromEmailPassword);

        for (Friend friend : friends) {
            if (isBirthdayToday(friend.getDateOfBirth())) {
                String subject = "Happy Birthday!";
                String body = "Dear " + friend.getFirstName() + ",\n\nHappy Birthday!\n\nBest wishes,\nBirthday Reminder";
                emailService.sendEmail(friend.getEmail(), subject, body);
            }
        }

    }
    private static boolean isBirthdayToday(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        if (today.getMonth() == dateOfBirth.getMonth() && today.getDayOfMonth() == dateOfBirth.getDayOfMonth()) {
            return true;
        } else
            return today.getMonthValue() == 2 && today.getDayOfMonth() == 28 &&
                    dateOfBirth.getMonthValue() == 2 && dateOfBirth.getDayOfMonth() == 29 &&
                    !today.isLeapYear();
    }
}

