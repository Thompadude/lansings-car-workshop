package nu.lansingcarworkshop.servlets.person;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.UserActions;
import nu.lansingcarworkshop.servlets.helpers.setcontext.SetEntityClassesContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadPersonServlet", urlPatterns = "/ReadPersonServlet")
public class ReadPersonServlet extends HttpServlet {

    private boolean actionsSuccessful;
    private UserActions userAction;
    private SetEntityClassesContext setEntityClassesContext = new SetEntityClassesContext();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserAction(request.getParameter("action"));

        actionsSuccessful = checkUserActionAndSetContext(request);

        redirectToCorrectJsp(request, response);
    }

    private boolean checkUserActionAndSetContext(HttpServletRequest request) {
        if (userAction == UserActions.VIEWPERSONLIST) {
            actionsSuccessful = setPersonList();
        } else {
            actionsSuccessful = setCurrentPerson(request);
        }
        return actionsSuccessful;
    }

    private boolean setPersonList() {
        actionsSuccessful = setEntityClassesContext.setEmployeeList(getServletContext());
        if (actionsSuccessful) {
            actionsSuccessful = setEntityClassesContext.setCustomerList(getServletContext());
        }
        return actionsSuccessful;
    }

    private boolean setCurrentPerson(HttpServletRequest request) {
        String personId = request.getParameter("personId");
        actionsSuccessful = setEntityClassesContext.setCurrentPerson(getServletContext(), personId);
        return actionsSuccessful;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (actionsSuccessful) {
            redirectToCorrectJspWhenActionsIsSuccessful(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/error.jsp");
        }
    }

    private void redirectToCorrectJspWhenActionsIsSuccessful(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GetRedirectUrl getRedirectUrl = new GetRedirectUrl();
        String redirectUrl = getRedirectUrl.getRedirectUrl(userAction, request);
        response.sendRedirect(redirectUrl);
    }

    public void setUserAction(String action) {
        if (action.equalsIgnoreCase("list-persons")) {
            this.userAction = UserActions.VIEWPERSONLIST;
        } else if (action.equalsIgnoreCase("view-person-profile")) {
            this.userAction = UserActions.VIEWPERSON;
        } else if (action.equalsIgnoreCase("update-person-profile")) {
            this.userAction = UserActions.UPDATEPERSON;
        } else if (action.equalsIgnoreCase("add-vehicle-to-person")) {
            this.userAction = UserActions.ADDVEHICLETOPERSON;
        }
    }

}