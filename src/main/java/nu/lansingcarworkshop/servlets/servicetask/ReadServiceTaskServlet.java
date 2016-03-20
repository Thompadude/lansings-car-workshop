package nu.lansingcarworkshop.servlets.servicetask;

import nu.lansingcarworkshop.servlets.helpers.SetContextAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadServiceTaskServlet")
public class ReadServiceTaskServlet extends HttpServlet {

    private boolean isActionsSuccessfullyExecuted;
    private SetContextAttributes setContextAttributes = new SetContextAttributes();
    private String action;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        if (action.equalsIgnoreCase("listservicetasks")) {
            isActionsSuccessfullyExecuted = setContextAttributes.setServiceTasksList(getServletContext());
        } else {
            String serviceTaskId = request.getParameter("serviceTaskId");
            isActionsSuccessfullyExecuted = setContextAttributes.setCurrentServiceTask(getServletContext(), serviceTaskId);
            if (action.equalsIgnoreCase("updateprofile") && isActionsSuccessfullyExecuted) {
                setContextAttributes.setEmployeeList(getServletContext());
            }
        }

        if (isActionsSuccessfullyExecuted) {
            redirectToCorrectJsp(request, response);
        } else {
            //TODO redirect to error page.
        }
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (action) {
            case "listservicetasks":
                response.sendRedirect(request.getContextPath() + "/servicetask/servicetask-list.jsp");
                break;
            case "viewprofile":
                response.sendRedirect(request.getContextPath() + "/servicetask/servicetask-profile.jsp");
                break;
            case "updateprofile":
                response.sendRedirect(request.getContextPath() + "/servicetask/servicetask-update.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "login.jsp");
        }
    }

}
