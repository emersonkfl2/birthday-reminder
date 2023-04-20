package application;

import org.birthday.application.BirthdayMessageSender;
import org.birthday.application.MessageComposer;
import org.birthday.domain.Friend;
import org.birthday.domain.MessageSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class BirthdayMessageSenderTest {

    private BirthdayMessageSender birthdayMessageSender;
    private MessageSender emailSender;
    private MessageSender smsSender;
    private MessageComposer messageComposer;

    @BeforeEach
    void setUp() {
        emailSender = Mockito.mock(MessageSender.class);
        smsSender = Mockito.mock(MessageSender.class);
        messageComposer = Mockito.mock(MessageComposer.class);
        birthdayMessageSender = new BirthdayMessageSender(emailSender, smsSender, messageComposer);
    }

    @Test
    void sendBirthdayMessage() {
        Friend friend = new Friend("Doe", "John", LocalDate.of(2000, 1, 1), "john.doe@example.com", "1234567890");
        String subject = "Happy birthday!";
        String message = "Happy birthday, dear John!";

        when(messageComposer.composeBirthdaySubject()).thenReturn(subject);
        when(messageComposer.composeBirthdayMessage(friend.getFirstName())).thenReturn(message);

        birthdayMessageSender.sendBirthdayMessage(friend);

        verify(emailSender).sendMessage(friend.getEmail(), subject, message);
        verify(smsSender).sendMessage(friend.getPhoneNumber(), subject, message);
    }

    @Test
    void sendBirthdayReminder() {
        Friend friend = new Friend("Ann", "Mary", LocalDate.of(2000, 1, 1), "jane.doe@example.com", "0987654321");
        Friend birthdayFriend = new Friend("Smith", "John", LocalDate.of(2000, 1, 1), "john.smith@example.com", "1234567890");
        String subject = "Birthday Reminder";
        String message = "Dear Jane,\n" +
                "Today is John Smith's birthday.\n" +
                "Don't forget to send him a message!";
        //Ann,Mary,1975-09-11,mary.ann@foobar.com,555-111-4457
        when(messageComposer.composeReminderSubject()).thenReturn(subject);
        when(messageComposer.composeReminderMessage(friend.getFirstName(), birthdayFriend.getFirstName(), birthdayFriend.getLastName())).thenReturn(message);

        birthdayMessageSender.sendBirthdayReminder(friend, birthdayFriend);

        verify(emailSender).sendMessage(friend.getEmail(), subject, message);
        verify(smsSender).sendMessage(friend.getPhoneNumber(), subject, message);
    }
}
