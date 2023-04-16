package org.birthday.infrastructure;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlatFileFriendRepository implements FriendRepository {
    private String filePath;

    public FlatFileFriendRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Friend> findAll() {
        List<Friend> friends = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length == 4) {
                    String lastName = tokens[0];
                    String firstName = tokens[1];
                    LocalDate dateOfBirth = LocalDate.parse(tokens[2], dateFormatter);
                    String email = tokens[3];

                    Friend friend = new Friend(lastName, firstName, dateOfBirth, email);
                    friends.add(friend);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading friends from flat file: " + e.getMessage());
        }

        return friends;
    }
}


