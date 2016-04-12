package test.nu.lansingcarworkshop.servlets.helpers.setcontext;

import nu.lansingcarworkshop.servlets.helpers.setcontext.SetEntityClassesContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SetEntityClassesContextTest extends Mockito {

    @Mock
    private SetEntityClassesContext mockedSetEntityClassesContext;
    @Mock
    private ServletContext mockedServletContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        mockedSetEntityClassesContext = null;
        mockedServletContext = null;
    }

    @Test
    public void testSetCurrentPerson_Should_Return_False() throws Exception {
        when(mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, null)).thenReturn(false);
        when(mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, "")).thenReturn(false);

        boolean result1 = mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, null);
        boolean result2 = mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, "");

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void testSetCurrentPerson_Should_Return_True() throws Exception {
        when(mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, "1")).thenReturn(true);
        boolean result = mockedSetEntityClassesContext.setCurrentPerson(mockedServletContext, "1");

        assertTrue(result);
    }

    @Test
    public void testSetPersonList_Should_Return_False_Since_No_Person_List_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setPersonList(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetCustomerList_Should_Return_False_Since_No_Customer_List_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setCustomerList(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetEmployeeList_Should_Return_False_Since_No_Employee_List_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setEmployeeList(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetCurrentVehicle_Should_Return_False() throws Exception {
        when(mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, null)).thenReturn(false);
        when(mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, "")).thenReturn(false);

        boolean result1 = mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, null);
        boolean result2 = mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, "");

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void testSetCurrentVehicle_Should_Return_True() throws Exception {
        when(mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, "1")).thenReturn(true);
        boolean result = mockedSetEntityClassesContext.setCurrentVehicle(mockedServletContext, "1");

        assertTrue(result);
    }

    @Test
    public void testSetVehicleList_Should_Return_False_Since_No_Vehicle_List_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setVehicleList(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetServiceTasksLists_Should_Return_False_Since_No_Service_Tasks_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setServiceTasksLists(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetUpcomingServiceTasksLists_Should_Return_False_Since_No_Service_Tasks_Exists() throws Exception {
        boolean result = mockedSetEntityClassesContext.setUpcomingServiceTasksLists(mockedServletContext);
        assertFalse(result);
    }

    @Test
    public void testSetServiceTasksListByVehicle_Should_Return_True() throws Exception {
        when(mockedSetEntityClassesContext.setServiceTasksListByVehicle(mockedServletContext, "1")).thenReturn(true);
        boolean result = mockedSetEntityClassesContext.setServiceTasksListByVehicle(mockedServletContext, "1");

        assertTrue(result);
    }

    @Test
    public void testSetCurrentServiceTask_Should_Return_True() throws Exception {
        when(mockedSetEntityClassesContext.setCurrentServiceTask(mockedServletContext, "1")).thenReturn(true);
        boolean result = mockedSetEntityClassesContext.setCurrentServiceTask(mockedServletContext, "1");

        assertTrue(result);
    }

}