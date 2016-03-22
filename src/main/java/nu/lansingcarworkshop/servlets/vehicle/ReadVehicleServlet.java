package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.SetContextAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadVehicleServlet")
public class ReadVehicleServlet extends HttpServlet {

    private boolean isActionsSuccessfullyExecuted;
    private SetContextAttributes setContextAttributes = new SetContextAttributes();
    private String action;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        isActionsSuccessfullyExecuted = checkUserActionAndReactAccordingly(request);

        redirectToCorrectJsp(request, response);
    }

    /**
     * If the user do not request a list of vehicles the user has requested to view a profile.
     * Update the correct context.
     *
     * @param request the request from the client.
     * @return true if action is successfully executed.
     */
    private boolean checkUserActionAndReactAccordingly(HttpServletRequest request) {
        if (action.equalsIgnoreCase("listvehicles")) {
            isActionsSuccessfullyExecuted = setContextAttributes.setVehicleList(getServletContext());
        } else {
            String vehicleId = request.getParameter("vehicleId");
            isActionsSuccessfullyExecuted = setContextAttributes.setCurrentVehicle(getServletContext(), vehicleId);
            isActionsSuccessfullyExecuted = setContextAttributes.setServiceTasksListByVehicle(getServletContext(), vehicleId);
            if (action.equalsIgnoreCase("createservicetask") && isActionsSuccessfullyExecuted) {
                isActionsSuccessfullyExecuted = setContextAttributes.setEmployeeList(getServletContext());
            }
        }
        return isActionsSuccessfullyExecuted;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isActionsSuccessfullyExecuted) {
            GetRedirectUrl getRedirectUrl = new GetRedirectUrl();
            String redirectUrl = getRedirectUrl.getReadVehicleServletRedirectUrl(action, request);
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/error.jsp");
        }
    }

}