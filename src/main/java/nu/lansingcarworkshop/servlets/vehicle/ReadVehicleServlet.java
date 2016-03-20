package nu.lansingcarworkshop.servlets.vehicle;

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

        if (isActionsSuccessfullyExecuted) {
            redirectToCorrectJsp(request, response);
        } else {
            //TODO redirect to error page.
        }
    }

    private boolean checkUserActionAndReactAccordingly(HttpServletRequest request) {
        if (action.equalsIgnoreCase("listvehicles")) {
            isActionsSuccessfullyExecuted = setContextAttributes.setVehicleList(getServletContext());
        } else {
            String vehicleId = request.getParameter("vehicleId");
            isActionsSuccessfullyExecuted = setContextAttributes.setCurrentVehicle(getServletContext(), vehicleId);
            if (action.equalsIgnoreCase("createservicetask") && isActionsSuccessfullyExecuted) {
                isActionsSuccessfullyExecuted = setContextAttributes.setEmployeeList(getServletContext());
            }
        }
        return isActionsSuccessfullyExecuted;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (action) {
            case "listvehicles":
                response.sendRedirect(request.getContextPath() + "/vehicle/vehicles-list.jsp");
                break;
            case "viewprofile":
                response.sendRedirect(request.getContextPath() + "/vehicle/vehicle-profile.jsp");
                break;
            case "updateprofile":
                response.sendRedirect(request.getContextPath() + "/vehicle/vehicles-update.jsp");
                break;
            case "createservicetask":
                response.sendRedirect(request.getContextPath() + "/servicetask/servicetask-create.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "login.jsp");
        }
    }


}