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
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail()
                    + ", Phone Number: " + friend.getPhoneNumber());
        }

        //Test FlatFile FriendRepository
        FriendRepository friendFileRepository = new FlatFileFriendRepository(Path.of("friends.txt"));
        List<Friend> friendsFile = friendFileRepository.findAll();

        System.out.println("\n------------Getting friends from file------------");
        for (Friend friend : friendsFile) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() +
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail()
                    + ", Phone Number: " + friend.getPhoneNumber());
        }
    }
}

