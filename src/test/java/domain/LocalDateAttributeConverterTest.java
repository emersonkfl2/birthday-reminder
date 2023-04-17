package domain;

import org.birthday.domain.LocalDateAttributeConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocalDateAttributeConverterTest {

    private LocalDateAttributeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new LocalDateAttributeConverter();
    }

    @Test
    void shouldConvertToDatabaseColumn() {
        LocalDate localDate = LocalDate.of(2023, 4, 15);
        String expectedDateString = "2023-04-15";

        String dateString = converter.convertToDatabaseColumn(localDate);

        assertEquals(expectedDateString, dateString);
    }

    @Test
    void shouldConvertToEntityAttribute() {
        String dateString = "2023-04-15";
        LocalDate expectedLocalDate = LocalDate.of(2023, 4, 15);

        LocalDate localDate = converter.convertToEntityAttribute(dateString);

        assertEquals(expectedLocalDate, localDate);
    }

    @Test
    void shouldHandleNullLocalDateWhenConvertingToDatabaseColumn() {
        String dateString = converter.convertToDatabaseColumn(null);
        assertNull(dateString);
    }

    @Test
    void shouldHandleNullDateStringWhenConvertingToEntityAttribute() {
        LocalDate localDate = converter.convertToEntityAttribute(null);
        assertNull(localDate);
    }
}
