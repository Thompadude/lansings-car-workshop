package test.nu.lansingcarworkshop.services.converters;

import nu.lansingcarworkshop.services.converters.LocalDateConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class LocalDateConverterTest {

    LocalDateConverter localDateConverter;

    @Before
    public void setUp() throws Exception {
        localDateConverter = new LocalDateConverter();
    }

    @After
    public void tearDown() throws Exception {
        localDateConverter = null;
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Not_Return_Null_When_Argument_Is_Valid_LocalDate() throws Exception {
        Date result = localDateConverter.convertToDatabaseColumn(LocalDate.now());
        assertNotNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Not_Return_Null_When_Argument_Is_Null() throws Exception {
        Date result = localDateConverter.convertToDatabaseColumn(null);
        assertNotNull(result);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Return_Date_Of_Today_In_Date_When_Argument_Is_Null_Ignoring_Nano_Seconds() {
        Date result = localDateConverter.convertToDatabaseColumn(null);

        assertTrue(result.compareTo(Date.valueOf(LocalDate.now())) == 0);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Return_Correct_Formatted_Date() {
        Date result = localDateConverter.convertToDatabaseColumn(LocalDate.of(2020, 3, 4));
        Date expectedResult = Date.valueOf("2020-03-04");

        assertEquals(expectedResult, result);
    }

    @Test(expected = DateTimeParseException.class)
    public void testConvertToDatabaseColumn_Should_Throw_DateTimeParseException() {
        localDateConverter.convertToDatabaseColumn(LocalDate.parse("David Hasselhoff"));
    }

    @Test
    public void testConvertToEntityAttribute_Should_Not_Return_Null_When_Argument_Is_Valid_Date() {
        LocalDate result = localDateConverter.convertToEntityAttribute(Date.valueOf("2020-03-04"));
        assertNotNull(result);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Not_Return_Null_When_Argument_Is_Null() {
        LocalDate result = localDateConverter.convertToEntityAttribute(null);
        assertNotNull(result);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Return_Date_Of_Today_In_LocalDate_When_Argument_Is_Null() {
        LocalDate result = localDateConverter.convertToEntityAttribute(null);

        assertTrue(result.compareTo(LocalDate.now()) == 0);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Return_Correct_Formatted_LocalDate() {
        LocalDate result = localDateConverter.convertToEntityAttribute(Date.valueOf("2020-03-04"));
        LocalDate expectedResult = LocalDate.of(2020, 3, 4);

        assertEquals(expectedResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToEntityAttribute_Should_Throw_IllegalArgumentParseException() {
        localDateConverter.convertToEntityAttribute(Date.valueOf("David Hasselhoff"));
    }

}