package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.vehicle.ReadVehicle;
import nu.lansingcarworkshop.services.vehicle.UpdateVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "UpdateVehicleServlet")
public class UpdateVehicleServlet extends HttpServlet {

    private String registrationPlate, make, fuel;
    private LocalDate modelYear;
    private Vehicle vehicleWithUpdatedAttributes;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int vehicleId = Integer.parseInt(request.getParameter("vehicleid"));
        ReadVehicle readVehicle = new ReadVehicle();
        vehicleWithUpdatedAttributes = readVehicle.getVehicleById(vehicleId);

        initializeVariablesFromPostRequest(request);

        setNewAttributes(request);

        UpdateVehicle updateVehicle = new UpdateVehicle();
        updateVehicle.updateVehicle(vehicleWithUpdatedAttributes);

        response.sendRedirect("/ReadVehicleServlet?vehicleId=" + vehicleWithUpdatedAttributes.getId() + "&action=viewprofile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        registrationPlate = request.getParameter("vehicle-registrationplate").toUpperCase();
        make = request.getParameter("vehicle-make");
        fuel = request.getParameter("vehicle-fueltype");
        modelYear = LocalDate.parse(request.getParameter("vehicle-modelyear"));
    }

    private void setNewAttributes(HttpServletRequest request) {
        vehicleWithUpdatedAttributes.setRegistrationPlate(registrationPlate);
        vehicleWithUpdatedAttributes.setMake(make);
        vehicleWithUpdatedAttributes.setModelYear(modelYear);
        vehicleWithUpdatedAttributes.setFuel(fuel);
    }

}