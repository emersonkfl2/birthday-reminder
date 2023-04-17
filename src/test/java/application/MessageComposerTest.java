package application;

import org.birthday.application.MessageComposer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageComposerTest {

    private MessageComposer messageComposer;

    @BeforeEach
    void setUp() {
        messageComposer = new MessageComposer();
    }

    @Test
    void composeBirthdaySubject() {
        String expectedSubject = "Happy birthday!";
        assertEquals(expectedSubject, messageComposer.composeBirthdaySubject());
    }

    @Test
    void composeBirthdayMessage() {
        String firstName = "John";
        String expectedMessage = "Happy birthday, dear John!";
        assertEquals(expectedMessage, messageComposer.composeBirthdayMessage(firstName));
    }

    @Test
    void composeReminderSubject() {
        String expectedSubject = "Birthday Reminder";
        assertEquals(expectedSubject, messageComposer.composeReminderSubject());
    }

    @Test
    void composeReminderMessage() {
        String firstName = "Jane";
        String someoneElseFirstName = "John";
        String someoneElseLastName = "Doe";
        String expectedMessage = "Dear Jane,\n" +
                "Today is John Doe's birthday.\n" +
                "Don't forget to send them a message!";
        assertEquals(expectedMessage, messageComposer.composeReminderMessage(firstName, someoneElseFirstName, someoneElseLastName));
    }
}
