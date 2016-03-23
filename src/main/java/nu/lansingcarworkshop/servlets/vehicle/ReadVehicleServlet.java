package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.servlets.helpers.GetRedirectUrl;
import nu.lansingcarworkshop.servlets.helpers.SetContextAttributes;
import nu.lansingcarworkshop.servlets.helpers.UserActions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadVehicleServlet")
public class ReadVehicleServlet extends HttpServlet {

    private boolean actionsSuccessful;
    private SetContextAttributes setContextAttributes = new SetContextAttributes();
    private UserActions userAction;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUserAction(request.getParameter("action"));

        actionsSuccessful = checkUserActionAndSetContext(request);

        redirectToCorrectJsp(request, response);
    }

    private boolean checkUserActionAndSetContext(HttpServletRequest request) {
        if (userAction == UserActions.VIEWVEHICLELIST) {
            actionsSuccessful = setContextAttributes.setVehicleList(getServletContext());
        } else {
            actionsSuccessful = setCurrentVehicleAndThatVehiclesServiceTasks(request);
            if (userAction == UserActions.CREATESERVICETASK && actionsSuccessful) {
                actionsSuccessful = setContextAttributes.setEmployeeList(getServletContext());
            }
        }
        return actionsSuccessful;
    }

    private boolean setCurrentVehicleAndThatVehiclesServiceTasks(HttpServletRequest request) {
        String vehicleId = request.getParameter("vehicleId");
        actionsSuccessful = setContextAttributes.setCurrentVehicle(getServletContext(), vehicleId);
        actionsSuccessful = setContextAttributes.setServiceTasksListByVehicle(getServletContext(), vehicleId);
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
        if (action.equalsIgnoreCase("list-vehicles")) {
            this.userAction = UserActions.VIEWVEHICLELIST;
        } else if (action.equalsIgnoreCase("view-vehicle")) {
            this.userAction = UserActions.VIEWVEHICLE;
        } else if (action.equalsIgnoreCase("update-vehicle")) {
            this.userAction = UserActions.UPDATEVEHICLE;
        } else if (action.equalsIgnoreCase("create-service-task")) {
            this.userAction = UserActions.CREATESERVICETASK;
        }
    }

}