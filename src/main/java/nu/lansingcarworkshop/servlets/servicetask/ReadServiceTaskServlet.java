package nu.lansingcarworkshop.servlets.servicetask;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.SetContextAttributes;
import nu.lansingcarworkshop.servlets.helpers.UserActions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadServiceTaskServlet")
public class ReadServiceTaskServlet extends HttpServlet {

    private boolean actionsSuccessful;
    private SetContextAttributes setContextAttributes = new SetContextAttributes();
    private UserActions userAction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserAction(request.getParameter("action"));

        actionsSuccessful = checkUserActionAndReactAccordingly(request);

        redirectToCorrectJsp(request, response);
    }

    private boolean checkUserActionAndReactAccordingly(HttpServletRequest request) {
        if (userAction == UserActions.VIEWSERVICETASKLIST || userAction == UserActions.VIEWUPCOMINGSERVICETASKS) {
            setServiceTaskLists();
        } else {
            setCurrentServiceTask(request);
            if (userAction == UserActions.UPDATESERVICETASK && actionsSuccessful) {
                setContextAttributes.setEmployeeList(getServletContext());
            }
        }
        return actionsSuccessful;
    }

    private boolean setServiceTaskLists() {
        actionsSuccessful = setContextAttributes.setServiceTasksLists(getServletContext());
        actionsSuccessful = setContextAttributes.setUpcomingServiceTasksLists(getServletContext());
        return actionsSuccessful;
    }

    private boolean setCurrentServiceTask(HttpServletRequest request) {
        String serviceTaskId = request.getParameter("serviceTaskId");
        actionsSuccessful = setContextAttributes.setCurrentServiceTask(getServletContext(), serviceTaskId);
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
        if (action.equalsIgnoreCase("list-service-tasks")) {
            this.userAction = UserActions.VIEWSERVICETASKLIST;
        } else if (action.equalsIgnoreCase("view-service-task-profile")) {
            this.userAction = UserActions.VIEWSERVICETASK;
        } else if (action.equalsIgnoreCase("update-service-task")) {
            this.userAction = UserActions.UPDATESERVICETASK;
        } else if (action.equalsIgnoreCase("list-upcoming-service-tasks")) {
            this.userAction = UserActions.VIEWUPCOMINGSERVICETASKS;
        }
    }

}