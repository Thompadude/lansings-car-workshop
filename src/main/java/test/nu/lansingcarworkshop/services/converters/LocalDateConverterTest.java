package test.nu.lansingcarworkshop.services.converters;

import nu.lansingcarworkshop.services.converters.LocalDateConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testConvertToDatabaseColumn_Should_Not_Return_Null() throws Exception {
        Date result1 = localDateConverter.convertToDatabaseColumn(LocalDate.now());
        Date result2 = localDateConverter.convertToDatabaseColumn(null);
        assertNotNull(result1);
        assertNotNull(result2);
    }

    @Test
    public void testConvertToDatabaseColumn_Should_Return_Correct_Formatted_Date() throws Exception {
        Date result = localDateConverter.convertToDatabaseColumn(LocalDate.of(2020, 3, 4));
        assertEquals(Date.valueOf("2020-03-04"), result);
    }

    @Test(expected = DateTimeParseException.class)
    public void testConvertToDatabaseColumn_Should_Throw_DateTimeParseException() throws Exception {
        localDateConverter.convertToDatabaseColumn(LocalDate.parse("David Hasselhoff"));
    }

    @Test
    public void testConvertToEntityAttribute_Should_Not_Return_Null() throws Exception {
        LocalDate result1 = localDateConverter.convertToEntityAttribute(Date.valueOf("2020-03-04"));
        LocalDate result2 = localDateConverter.convertToEntityAttribute(null);
        assertNotNull(result1);
        assertNotNull(result2);
    }

    @Test
    public void testConvertToEntityAttribute_Should_Return_Correct_Formatted_LocalDate() throws Exception {
        LocalDate result = localDateConverter.convertToEntityAttribute(Date.valueOf("2020-03-04"));
        assertEquals(LocalDate.of(2020, 3, 4), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToEntityAttribute_Should_Throw_DateTimeParseException() throws Exception {
        localDateConverter.convertToEntityAttribute(Date.valueOf("David Hasselhoff"));
    }

}