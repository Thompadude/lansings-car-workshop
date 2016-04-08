package test.nu.lansingcarworkshop.services.converters;

import nu.lansingcarworkshop.services.converters.LocalDateTimeConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class LocalDateTimeConverterTest {

    LocalDateTimeConverter localDateTimeConverter;

    @Before
    public void setUp() throws Exception {
        localDateTimeConverter = new LocalDateTimeConverter();
    }

    @After
    public void tearDown() throws Exception {
        localDateTimeConverter = null;
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Not_Return_Null_When_Argument_Is_Valid_LocalDateTime() {
        Timestamp result = localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.now());
        assertNotNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Not_Return_Null_When_Argument_Is_Null() {
        Timestamp result = localDateTimeConverter.convertToDatabaseColumn(null);
        assertNotNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Return_Date_Of_Today_In_Timestamp_When_Argument_Is_Null_Ignoring_Nano_Seconds() {
        Timestamp result = localDateTimeConverter.convertToDatabaseColumn(null);

        assertTrue(result.compareTo(Timestamp.valueOf(LocalDateTime.now())) == 0);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Return_Correct_Formatted_LocalDateTime() {
        Timestamp result = localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.of(2020, 12, 12, 12, 4, 4));
        Timestamp expectedResult = Timestamp.valueOf("2020-12-12 12:04:04");

        assertEquals(expectedResult, result);
    }

    @Test(expected = DateTimeParseException.class)
    public void testConvertToEntityAttribute_Should_Throw_DateTimeParseException() {
        localDateTimeConverter.convertToDatabaseColumn(LocalDateTime.parse("David Hasselhoff"));
    }

    @Test
    public void testConvertToEntityAttribute_Should_Not_Return_Null_When_Argument_Is_Valid_Timestamp() {
        LocalDateTime result = localDateTimeConverter.convertToEntityAttribute(Timestamp.valueOf("2020-12-12 12:04:04"));
        assertNotNull(result);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Not_Return_Null_When_Argument_Is_Null() {
        LocalDateTime result = localDateTimeConverter.convertToEntityAttribute(null);
        assertNotNull(result);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Return_Date_Of_Today_In_LocalDateTime_When_Argument_Is_Null_Ignoring_Nano_Seconds() {
        LocalDateTime result = localDateTimeConverter.convertToEntityAttribute(null);

        assertTrue(result.withNano(0).compareTo(LocalDateTime.now().withNano(0)) == 0);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Return_Correct_Formatted_LocalDateTime() {
        LocalDateTime result = localDateTimeConverter.convertToEntityAttribute(Timestamp.valueOf("2020-12-12 12:04:04"));
        LocalDateTime expectedResult = LocalDateTime.of(2020, 12, 12, 12, 4, 4);

        assertTrue(result.compareTo(expectedResult) == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToEntityAttribute_Should_Throw_IllegalArgumentParseException() {
        localDateTimeConverter.convertToEntityAttribute(Timestamp.valueOf("David Hasselhoff"));
    }

}