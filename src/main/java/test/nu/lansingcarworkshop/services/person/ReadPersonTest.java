package test.nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;
import nu.lansingcarworkshop.services.person.ReadPerson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ReadPersonTest {

    @Mock
    private ReadPerson mockedReadPerson;
    @Mock
    private EntityManagerFacade mockedEntityManagerFacade;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        mockedReadPerson = null;
        mockedEntityManagerFacade = null;
    }

    @Test
    public void testGetPersonById() throws Exception {
        Person expectedPerson = new Customer();
        mockedEntityManagerFacade.beginTransactionSaveEntityAndCommit(expectedPerson);

        when(mockedReadPerson.getPersonById(1)).thenReturn(expectedPerson);

        Person result = mockedReadPerson.getPersonById(1);

        assertNotNull("Get person by id result is null!", result);
        assertEquals("Expected person is not equal to result.", result, expectedPerson);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer testCustomer1 = new Customer();
        Customer testCustomer2 = new Customer();
        @SuppressWarnings("unchecked") List<Customer> expectedListOfCustomers = mockedReadPerson.getAllCustomers();
        expectedListOfCustomers.add(testCustomer1);
        expectedListOfCustomers.add(testCustomer2);

        when(mockedReadPerson.getAllCustomers()).thenReturn(expectedListOfCustomers);

        List result = mockedReadPerson.getAllCustomers();

        assertNotNull("Customer list is null!", result);
        assertEquals("Expected list of customers is not equal to result", result, expectedListOfCustomers);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        Employee testEmployee1 = new Employee();
        Employee testEmployee2 = new Employee();
        @SuppressWarnings("unchecked") List<Employee> expectedListOfEmployees = mockedReadPerson.getAllEmployees();
        expectedListOfEmployees.add(testEmployee1);
        expectedListOfEmployees.add(testEmployee2);

        when(mockedReadPerson.getAllEmployees()).thenReturn(expectedListOfEmployees);

        List result = mockedReadPerson.getAllEmployees();

        assertNotNull("Employee list is null!", result);
        assertEquals("Expected list of employees is not equal to result", result, expectedListOfEmployees);
    }

    @Test
    public void testGetAllPersons() throws Exception {
        Employee testPerson1 = new Employee();
        Customer testPerson2 = new Customer();
        @SuppressWarnings("unchecked") List<Person> expectedListOfPersons = mockedReadPerson.getAllPersons();
        expectedListOfPersons.add(testPerson1);
        expectedListOfPersons.add(testPerson2);

        when(mockedReadPerson.getAllPersons()).thenReturn(expectedListOfPersons);

        List result = mockedReadPerson.getAllPersons();

        assertNotNull("Person list is null!", result);
        assertEquals("Expected list of persons is not equal to result", result, expectedListOfPersons);
    }

}