package org.birthday.application;

public class MessageComposer {

    public String composeBirthdaySubject() {
        return "Happy birthday!";
    }

    public String composeBirthdayMessage(String firstName) {
        return "Happy birthday, dear " + firstName + "!";
    }

    public String composeReminderSubject() {
        return "Birthday Reminder";
    }

    public String composeReminderMessage(String firstName, String someoneElseFirstName, String someoneElseLastName) {
        return "Dear " + firstName + ",\n" +
                "Today is " + someoneElseFirstName + " " + someoneElseLastName + "'s birthday.\n" +
                "Don't forget to send them a message!";
    }
}
