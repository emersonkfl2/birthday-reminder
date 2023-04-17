package infrastructure.sms;

import org.birthday.infrastructure.sms.SmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmsServiceTest {

    private SmsService smsService;

    @BeforeEach
    void setUp() {
        smsService = new SmsService();
    }

    @Test
    void sendMessage() {
        // Define input data
        String phoneNumber = "1234567890";
        String subject = "Test subject";
        String message = "Test message";

        // Capture standard output stream
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the method to test
        smsService.sendMessage(phoneNumber, subject, message);

        // Build the expected output
        String expectedOutput = "Number: " + phoneNumber + "\n" +
                "Subject: " + subject + "\n" +
                "Message: " + message + "\n";

        // Check if the actual output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }
}
