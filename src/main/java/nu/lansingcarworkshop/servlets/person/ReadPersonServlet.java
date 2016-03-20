package nu.lansingcarworkshop.servlets.person;

import nu.lansingcarworkshop.servlets.helpers.SetContextAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadPersonServlet")
public class ReadPersonServlet extends HttpServlet {

    private boolean isActionsSuccessfullyExecuted;
    private SetContextAttributes setContextAttributes = new SetContextAttributes();
    private String action;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        isActionsSuccessfullyExecuted = checkUserActionAndReactAccordingly(request);

        if (isActionsSuccessfullyExecuted) {
            redirectToCorrectJsp(request, response);
        } else {
            //TODO redirect to error page.
        }
    }

    private boolean checkUserActionAndReactAccordingly(HttpServletRequest request) {
        if (action.equalsIgnoreCase("listpersons")) {
            isActionsSuccessfullyExecuted = setContextAttributes.setPersonList(getServletContext());
        } else {
            String personId = request.getParameter("personId");
            isActionsSuccessfullyExecuted = setContextAttributes.setCurrentPerson(getServletContext(), personId);
        }
        return isActionsSuccessfullyExecuted;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (action) {
            case "addvehicle":
                response.sendRedirect(request.getContextPath() + "/person/person-addvehicle.jsp");
                break;
            case "listpersons":
                response.sendRedirect(request.getContextPath() + "/person/persons-edit-delete.jsp");
                break;
            case "viewprofile":
                response.sendRedirect(request.getContextPath() + "/person/person-profile.jsp");
                break;
            case "updateprofile":
                response.sendRedirect(request.getContextPath() + "/person/person-update.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

}