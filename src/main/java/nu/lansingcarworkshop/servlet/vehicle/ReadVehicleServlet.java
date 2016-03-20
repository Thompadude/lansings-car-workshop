package nu.lansingcarworkshop.servlet.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.vehicle.ReadVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadVehicleServlet")
public class ReadVehicleServlet extends HttpServlet {

    boolean isActionSuccessfullyExecuted;
    String action;
    Vehicle currentVehicle;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        if (action.equalsIgnoreCase("listvehicles")) {
            isActionSuccessfullyExecuted = setVehicleListServletAttribute();
        } else {
            currentVehicle = setCurrentVehicle(request);
            isActionSuccessfullyExecuted = setCurrentVehicleServletAttribute();
        }

        if (isActionSuccessfullyExecuted) {
            redirectToCorrectJsp(request, response);
        }
    }

    private Vehicle setCurrentVehicle(HttpServletRequest request) {
        String vehicleIdString = request.getParameter("vehicleId");

        if (vehicleIdString != null) {
            int vehicleId = Integer.parseInt(vehicleIdString);
            ReadVehicle readVehicle = new ReadVehicle();
            currentVehicle = readVehicle.getVehicleById(vehicleId);
        }
        return currentVehicle;
    }

    private boolean setCurrentVehicleServletAttribute() {
        if (currentVehicle != null) {
            getServletContext().setAttribute("currentVehicle", currentVehicle);
            return true;
        }
        return false;
    }

    private boolean setVehicleListServletAttribute() {
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllCars();

        if (vehicles != null) {
            getServletContext().setAttribute("listOfVehicles", vehicles);
            return true;
        }
        return false;
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
            default:
                response.sendRedirect(request.getContextPath() + "login.jsp");
        }
    }


}