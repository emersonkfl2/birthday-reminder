package org.birthday;

import org.birthday.domain.Friend;
import org.birthday.infrastructure.SQLiteFriendRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SQLiteFriendRepository friendRepository = new SQLiteFriendRepository();
        List<Friend> friends = friendRepository.findAll();

        for (Friend friend : friends) {
            System.out.println("Friend: " + friend.getFirstName() + " " + friend.getLastName() + ", Date of Birth: " +
                    friend.getFormattedDateOfBirth() + ", Email: " + friend.getEmail());
        }
    }
}

