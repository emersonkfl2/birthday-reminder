package infrastructure.email;

import org.birthday.infrastructure.email.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
    }

    @Test
    void sendMessage() {
        // Define input data
        String toEmail = "test@example.com";
        String subject = "Test subject";
        String message = "Test message";

        // Capture standard output stream
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the method to test
        emailService.sendMessage(toEmail, subject, message);

        // Build the expected output
        String expectedOutput = "Email: " + toEmail + "\n" +
                "Subject: " + subject + "\n" +
                "Message: " + message + "\n";

        // Check if the actual output matches the expected output
        assertEquals(expectedOutput, outContent.toString());
    }
}
