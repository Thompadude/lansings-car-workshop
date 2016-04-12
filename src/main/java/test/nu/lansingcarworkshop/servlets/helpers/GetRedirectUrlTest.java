package test.nu.lansingcarworkshop.servlets.helpers;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.UserActions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class GetRedirectUrlTest {

    private GetRedirectUrl getRedirectUrl;
    private String baseUrl;
    private String expectedRedirectUrl;
    private String result;
    @Mock
    private HttpServletRequest mockedHttpServletRequest;

    @Before
    public void setUp() throws Exception {
        getRedirectUrl = new GetRedirectUrl();
        MockitoAnnotations.initMocks(this);

        when(mockedHttpServletRequest.getContextPath()).thenReturn("the-root-to-your-web-app");
        baseUrl = mockedHttpServletRequest.getContextPath();
    }

    @After
    public void tearDown() throws Exception {
        getRedirectUrl = null;
        mockedHttpServletRequest = null;
        baseUrl = null;
        expectedRedirectUrl = null;
        result = null;
    }

    @Test
    public void testGetRedirectUrl_Add_Vehicle_To_Person() throws Exception {
        expectedRedirectUrl = baseUrl + "/person/add-vehicle-to-person.jsp";

        result = getRedirectUrl.getRedirectUrl(UserActions.ADDVEHICLETOPERSON, mockedHttpServletRequest);
        assertNotNull(result);
        assertEquals(result, expectedRedirectUrl);
    }

    @Test
    public void testGetRedirectUrl_View_Person_List() throws Exception {
        expectedRedirectUrl = baseUrl + "/person/persons-edit-delete.jsp";

        result = getRedirectUrl.getRedirectUrl(UserActions.VIEWPERSONLIST, mockedHttpServletRequest);
        assertNotNull(result);
        assertEquals(result, expectedRedirectUrl);
    }

    @Test
    public void testGetRedirectUrl_Should_Redirect_To_Error_Page() throws Exception {
        expectedRedirectUrl = baseUrl + "/error.jsp";

        result = getRedirectUrl.getRedirectUrl(null, mockedHttpServletRequest);
        assertNotNull(result);
        assertEquals(result, expectedRedirectUrl);
    }

}