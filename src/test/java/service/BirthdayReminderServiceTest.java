package service;

import org.birthday.domain.Friend;
import org.birthday.domain.FriendRepository;
import org.birthday.service.BirthdayReminderService;
import org.birthday.service.EmailService;
import org.birthday.service.SmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class BirthdayReminderServiceTest {

    private FriendRepository friendRepository;
    private EmailService emailService;
    private SmsService smsService;
    private BirthdayReminderService birthdayReminderService;

    @BeforeEach
    void setUp() {
        friendRepository = Mockito.mock(FriendRepository.class);
        emailService = Mockito.mock(EmailService.class);
        smsService = Mockito.mock(SmsService.class);
        birthdayReminderService = new BirthdayReminderService(friendRepository, emailService, smsService);
    }

    @Test
    void testSendBirthdayMessages() {
        // Add friends to the repository
        Friend friend1 = new Friend("Doe", "John", LocalDate.of(1990, 4, 15), "john@example.com", "1234567890");
        Friend friend2 = new Friend("Smith", "Jane", LocalDate.of(1992, 5, 20), "jane@example.com", "0987654321");
        List<Friend> friends = Arrays.asList(friend1, friend2);
        when(friendRepository.findAll()).thenReturn(friends);

        // Call the sendBirthdayMessages method
        birthdayReminderService.sendBirthdayMessages();

        // Verify that the emailService and smsService methods were called with the correct arguments
        // Add more test cases for different scenarios, such as when it's a friend's birthday, when it's not a friend's birthday, and when a friend is born on February 29th.
    }

    @Test
    void testSendBirthdayMessagesOnJohnsBirthday() {
        // Set today's date to April 15th
        LocalDate today = LocalDate.of(2023, 4, 15);

        // Add friends to the repository
        Friend friend1 = new Friend("Doe", "John", LocalDate.of(1990, 4, 15), "john@example.com", "1234567890");
        Friend friend2 = new Friend("Smith", "Jane", LocalDate.of(1992, 5, 20), "jane@example.com", "0987654321");
        List<Friend> friends = Arrays.asList(friend1, friend2);
        when(friendRepository.findAll()).thenReturn(friends);

        // Call the sendBirthdayMessages method
        birthdayReminderService.sendBirthdayMessages();

        // Verify that the emailService.sendEmail() method was called with the correct arguments for John's birthday
        String johnsBirthdaySubject = "Happy birthday!";
        String johnsBirthdayMessage = "Happy birthday, dear John!";
        verify(emailService).sendEmail(friend1.getEmail(), johnsBirthdaySubject, johnsBirthdayMessage);

        // Verify that the smsService.sendSms() method was called with the correct arguments for John's birthday
        String johnsFullName = friend1.getFirstName() + friend1.getLastName();
        verify(smsService).sendSms(johnsFullName, friend1.getPhoneNumber(), johnsBirthdaySubject, johnsBirthdayMessage);

        // Verify that the emailService.sendEmail() and smsService.sendSms() methods were called with the correct arguments for Jane's birthday reminder
        String janesReminderSubject = "Birthday Reminder";
        String janesReminderMessage = "Dear Jane,\nToday is John Doe's birthday.\nDon't forget to send him a message!";
        verify(emailService).sendEmail(friend2.getEmail(), janesReminderSubject, janesReminderMessage);
        String janesFullName = friend2.getFirstName() + friend2.getLastName();
        verify(smsService).sendSms(janesFullName, friend2.getPhoneNumber(), janesReminderSubject, janesReminderMessage);
    }

}
