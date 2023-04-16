package domain;

import org.birthday.domain.Friend;
import org.birthday.infrastructure.SQLiteFriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteFriendRepositoryTest {
    private SQLiteFriendRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SQLiteFriendRepository();
    }

    @Test
    void findAll_shouldReturnNonEmptyListOfFriends() {
        List<Friend> friends = repository.findAll();

        assertNotNull(friends, "The list of friends should not be null");
        assertFalse(friends.isEmpty(), "The list of friends should not be empty");
    }

    @Test
    void findAll_shouldReturnCorrectFriendData() {
        List<Friend> friends = repository.findAll();

        Friend friend = friends.stream()
                .filter(f -> f.getEmail().equalsIgnoreCase("john.doe@foobar.com"))
                .findFirst()
                .orElse(null);

        assertNotNull(friend, "John Doe should be in the list of friends");
        assertEquals("Doe", friend.getLastName(), "Last name should be Doe");
        assertEquals("John", friend.getFirstName(), "First name should be John");
        assertEquals("1982-10-08", friend.getFormattedDateOfBirth(), "Date of birth should be 1982-10-08");
    }

}
