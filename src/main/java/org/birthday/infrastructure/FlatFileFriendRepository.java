package org.birthday.infrastructure;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlatFileFriendRepository implements FriendRepository {


    private final Path filePath;

    public FlatFileFriendRepository(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Friend> findAll() {
        List<Friend> friends = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filePath.toUri()).toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] friendData = line.split(",");

                if (friendData.length != 5) {
                    System.err.println("Invalid data format: " + line);
                    continue;
                }
                String lastName = friendData[0].trim();
                String firstName = friendData[1].trim();
                LocalDate dateOfBirth = LocalDate.parse(friendData[2].trim(), formatter);
                String email = friendData[3].trim();
                String phoneNumber = friendData[4].trim();

                friends.add(new Friend(lastName, firstName, dateOfBirth, email, phoneNumber));
            }
        } catch (IOException e) {
            System.err.println("Error reading " + filePath + " file: " + e.getMessage());
        }

        return friends;
    }
}
