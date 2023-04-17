package application;

import org.birthday.application.BirthdayMessageScheduler;
import org.birthday.application.BirthdayMessageSender;
import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BirthdayMessageSchedulerTest {

    private FriendRepository friendRepository;
    private BirthdayMessageSender birthdayMessageSender;
    private BirthdayMessageScheduler birthdayMessageScheduler;

    @BeforeEach
    void setUp() {
        friendRepository = mock(FriendRepository.class);
        birthdayMessageSender = mock(BirthdayMessageSender.class);

        // Set the fixed date to October 8th, 2023
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2023, 10, 8, 0, 0).toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())), ZoneId.systemDefault());

        birthdayMessageScheduler = new BirthdayMessageScheduler(friendRepository, birthdayMessageSender, fixedClock);
    }


    @Test
    void shouldSendBirthdayMessageWhenItIsFriendsBirthday() {
        Friend john = new Friend("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com", "555-123-4556");
        Friend mary = new Friend("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com", "555-111-4457");
        Friend mike = new Friend("Tire", "Mike", LocalDate.of(1986, 5, 6), "mike.tire@foobar.com", "555-123-4463");

        List<Friend> friends = Arrays.asList(john, mary, mike);
        when(friendRepository.findAll()).thenReturn(friends);

        birthdayMessageScheduler.run();

        verify(birthdayMessageSender).sendBirthdayMessage(john);
    }

    @Test
    void shouldSendBirthdayReminderWhenItIsNotFriendsBirthday() {
        // Set the fixed date to September 11th, 2023 (Mary's birthday)
        Clock fixedClock = Clock.fixed(LocalDateTime.of(2023, 9, 11, 0, 0).toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())), ZoneId.systemDefault());
        birthdayMessageScheduler = new BirthdayMessageScheduler(friendRepository, birthdayMessageSender, fixedClock);

        Friend john = new Friend("Doe", "John", LocalDate.of(1982, 10, 8), "john.doe@foobar.com", "555-123-4556");
        Friend mary = new Friend("Ann", "Mary", LocalDate.of(1975, 9, 11), "mary.ann@foobar.com", "555-111-4457");
        Friend mike = new Friend("Tire", "Mike", LocalDate.of(1986, 5, 6), "mike.tire@foobar.com", "555-123-4463");

        List<Friend> friends = Arrays.asList(john, mary, mike);
        when(friendRepository.findAll()).thenReturn(friends);

        birthdayMessageScheduler.run();

        verify(birthdayMessageSender).sendBirthdayMessage(mary);
        verify(birthdayMessageSender).sendBirthdayReminder(john, mary);
        verify(birthdayMessageSender).sendBirthdayReminder(mike, mary);
    }
}
