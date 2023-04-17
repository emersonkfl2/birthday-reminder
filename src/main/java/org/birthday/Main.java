package org.birthday;

import org.birthday.application.BirthdayMessageScheduler;
import org.birthday.application.BirthdayMessageSender;
import org.birthday.application.MessageComposer;
import org.birthday.domain.FriendRepository;
import org.birthday.domain.MessageSender;
import org.birthday.infrastructure.ConsoleMessageSender;
import org.birthday.infrastructure.FlatFileFriendRepository;
import org.birthday.infrastructure.SQLiteFriendRepository;

import java.nio.file.Path;
import java.time.Clock;

public class Main {
    public static void main(String[] args) {
        // Choose the data source: SQLite database or flat text file
        FriendRepository friendRepository = new SQLiteFriendRepository("birthdayReminderPU");
        //FriendRepository friendRepository = new FlatFileFriendRepository(Path.of("friends.txt"));

        MessageSender consoleMessageSender = new ConsoleMessageSender();
        MessageComposer messageComposer = new MessageComposer();
        BirthdayMessageSender birthdayMessageSender = new BirthdayMessageSender(consoleMessageSender, consoleMessageSender, messageComposer);
        BirthdayMessageScheduler scheduler = new BirthdayMessageScheduler(friendRepository, birthdayMessageSender, Clock.systemDefaultZone());

        // Run the scheduler
        scheduler.run();
    }
}
