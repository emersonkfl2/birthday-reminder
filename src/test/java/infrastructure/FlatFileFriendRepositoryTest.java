package infrastructure;

import org.birthday.domain.Friend;
import org.birthday.infrastructure.FlatFileFriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlatFileFriendRepositoryTest {
    private FlatFileFriendRepository repository;

    private List<Friend> createExpectedFriendList() {
        return List.of(
                new Friend("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com", "555-123-4556"),
                new Friend("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com", "555-111-4457"),
                new Friend("Tire", "Mike", LocalDate.of(1986, 5, 6), "mike.tire@foobar.com", "555-123-4463")
        );
    }

    @BeforeEach
    void setUp() {
        Path filePath = Paths.get("src","test","java", "friends.txt");
        repository = new FlatFileFriendRepository(filePath);
    }

    @Test
    void findAllTest() {
        List<Friend> expectedFriends = createExpectedFriendList();
        List<Friend> actualFriends = repository.findAll();

        assertEquals(expectedFriends.size(), actualFriends.size());

        for (Friend expectedFriend : expectedFriends) {
            Friend actualFriend = actualFriends.stream()
                    .filter(friend -> friend.getFirstName().equals(expectedFriend.getFirstName()) &&
                            friend.getLastName().equals(expectedFriend.getLastName()))
                    .findFirst()
                    .orElse(null);

            assertNotNull(actualFriend);
            assertEquals(expectedFriend, actualFriend);
        }
    }
}
