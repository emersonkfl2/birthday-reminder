package infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.birthday.domain.Friend;
import org.birthday.infrastructure.SQLiteFriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteFriendRepositoryTest {
    private SQLiteFriendRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SQLiteFriendRepository("birthdayReminderTestPU");
        populateTestData();
    }

    void populateTestData() {
        EntityManager entityManager = repository.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.persist(new Friend("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"));
            entityManager.persist(new Friend("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com"));
            entityManager.persist(new Friend("Tire", "Mike", LocalDate.of(1986, 5, 6), "mike.tire@foobar.com"));

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }


    @Test
    void findAllTest() {
        List<Friend> friends = repository.findAll();
        assertEquals(3, friends.size());

        Friend john = friends.stream().filter(friend -> friend.getFirstName().equals("John")).findFirst().orElse(null);
        assertNotNull(john);
        assertEquals("Doe", john.getLastName(), "The last name should be Doe");
        assertEquals(LocalDate.of(1982, 10, 8), john.getDateOfBirth());
        assertEquals("john.doe@foobar.com", john.getEmail());

        Friend mary = friends.stream().filter(friend -> friend.getFirstName().equals("Mary")).findFirst().orElse(null);
        assertNotNull(mary);
        assertEquals("Ann", mary.getLastName(), "The last name should be Ann");
        assertEquals(LocalDate.of(1975, 9, 11), mary.getDateOfBirth());
        assertEquals("mary.ann@foobar.com", mary.getEmail());

        Friend mike = friends.stream().filter(friend -> friend.getFirstName().equals("Mike")).findFirst().orElse(null);
        assertNotNull(mike);
        assertEquals("Tire", mike.getLastName(), "The last name should be Tire");
        assertEquals(LocalDate.of(1986, 5, 6), mike.getDateOfBirth());
        assertEquals("mike.tire@foobar.com", mike.getEmail());
    }

    @Test
    void findAll_shouldReturnNonEmptyListOfFriends() {
        List<Friend> friends = repository.findAll();

        assertNotNull(friends, "The list of friends should not be null");
        assertFalse(friends.isEmpty(), "The list of friends should not be empty");
    }

}
