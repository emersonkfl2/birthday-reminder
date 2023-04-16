package org.birthday;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;
import org.birthday.infrastructure.FlatFileFriendRepository;
import org.birthday.infrastructure.SQLiteFriendRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Test JPA FriendRepository with SQLite
        FriendRepository friendRepository = new SQLiteFriendRepository();
        List<Friend> friends = friendRepository.findAll();

        System.out.println("Getting friends from SQLite");
        for (Friend friend : friends) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() +
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail());
        }

        //Test FlatFile FriendRepository
        FriendRepository friendFileRepository = new FlatFileFriendRepository();
        List<Friend> friendsFile = friendFileRepository.findAll();

        System.out.println("\nGetting friends from file");
        for (Friend friend : friendsFile) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() +
                    ", Date of Birth: " + friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail());
        }
    }
}

