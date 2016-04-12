package test.nu.lansingcarworkshop.servlets.helpers;

import nu.lansingcarworkshop.models.person.ContactInformation;
import nu.lansingcarworkshop.models.person.Role;
import nu.lansingcarworkshop.models.person.Sex;
import nu.lansingcarworkshop.servlets.helpers.PersonAttributeBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PersonAttributeBuilderTest {

    PersonAttributeBuilder personAttributeBuilder;
    @Mock
    HttpServletRequest mockedHttpServletRequest;

    @Before
    public void setUp() throws Exception {
        personAttributeBuilder = new PersonAttributeBuilder();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        personAttributeBuilder = null;
        mockedHttpServletRequest = null;
    }

    @Test
    public void testCreateContactInformation() throws Exception {
        when(mockedHttpServletRequest.getParameter("person-address")).thenReturn("Test Str 4C");
        when(mockedHttpServletRequest.getParameter("person-phone")).thenReturn("+1 123 123");
        ContactInformation result = personAttributeBuilder.createContactInformation(mockedHttpServletRequest);

        ContactInformation expectedContactInformation = new ContactInformation("Test Str 4C", "+1 123 123");

        assertNotNull("ContactInformation is null!", result);
        assertEquals("Address is not equal", expectedContactInformation.getAddress(), result.getAddress());
        assertEquals("Phone Number is not equal", expectedContactInformation.getPhonenumber(), result.getPhonenumber());
    }

    @Test
    public void testCreateSex() throws Exception {
        Sex result;

        when(mockedHttpServletRequest.getParameter("person-sex")).thenReturn("male");
        result = personAttributeBuilder.createSex(mockedHttpServletRequest);
        assertNotNull("Sex is null!", result);
        assertEquals("Sex is not MALE when param is \"male\"", result, Sex.MALE);

        when(mockedHttpServletRequest.getParameter("person-sex")).thenReturn("female");
        result = personAttributeBuilder.createSex(mockedHttpServletRequest);
        assertNotNull("Sex is null!", result);
        assertEquals("Sex is not FEMALE when param is \"female\"", result, Sex.FEMALE);

        when(mockedHttpServletRequest.getParameter("person-sex")).thenReturn("");
        result = personAttributeBuilder.createSex(mockedHttpServletRequest);
        assertNotNull("Sex is null!", result);
        assertEquals("Sex is not UNKNOWN when param is \"\"", result, Sex.UNKNOWN);

        when(mockedHttpServletRequest.getParameter("person-sex")).thenReturn(null);
        result = personAttributeBuilder.createSex(mockedHttpServletRequest);
        assertNotNull("Sex is null!", result);
        assertEquals("Sex is not UNKNOWN when param is null", result, Sex.UNKNOWN);

        when(mockedHttpServletRequest.getParameter("person-sex")).thenReturn("David Hasselhoff");
        result = personAttributeBuilder.createSex(mockedHttpServletRequest);
        assertNotNull("Sex is null!", result);
        assertEquals("Sex is not UNKNOWN when param faulty", result, Sex.UNKNOWN);
    }

    @Test
    public void testCreateRole() throws Exception {
        Role result;

        when(mockedHttpServletRequest.getParameter("person-role")).thenReturn("TECHNICIAN");
        result = personAttributeBuilder.createRole(mockedHttpServletRequest);
        assertNotNull("Role is null!", result);
        assertEquals("Role is not TECHNICIAN when param is \"TECHNICIAN\"", result, Role.TECHNICIAN);

        when(mockedHttpServletRequest.getParameter("person-role")).thenReturn("SPECIALIST");
        result = personAttributeBuilder.createRole(mockedHttpServletRequest);
        assertNotNull("Role is null!", result);
        assertEquals("Role is not SPECIALIST when param is \"TECHNICIAN\"", result, Role.SPECIALIST);

        when(mockedHttpServletRequest.getParameter("person-role")).thenReturn("");
        result = personAttributeBuilder.createRole(mockedHttpServletRequest);
        assertNotNull("Role is null!", result);
        assertEquals("Role is not NONE when param is \"\"", result, Role.NONE);

        when(mockedHttpServletRequest.getParameter("person-role")).thenReturn(null);
        result = personAttributeBuilder.createRole(mockedHttpServletRequest);
        assertNotNull("Role is null!", result);
        assertEquals("Role is not NONE when param is null", result, Role.NONE);
    }

}