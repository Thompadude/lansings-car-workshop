package nu.lansingcarworkshop.servlet.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Car;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.person.AddVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "CreateVehicleServlet")
public class CreateVehicleServlet extends HttpServlet {

    private String registrationPlate, make, fuel;
    private LocalDate modelYear;
    private Vehicle newVehicle;
    private int customerId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        initializeVariablesFromPostRequest(request);

        createAndAddVehicleToDatabase();

        response.getWriter().print("Vehicle with plate " + newVehicle.getRegistrationPlate() + " created.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        registrationPlate = request.getParameter("vehicle-registrationplate");
        make = request.getParameter("vehicle-make");
        fuel = request.getParameter("vehicle-fueltype");
        modelYear = LocalDate.parse(request.getParameter("vehicle-modelyear"));
        customerId = Integer.parseInt(request.getParameter("customerId"));
    }

    private void createAndAddVehicleToDatabase() {
        // TODO might change this if more than cars is developed into the application.
        newVehicle = new Car(registrationPlate, make, modelYear, fuel);
        AddVehicle addVehicle = new AddVehicle();
        addVehicle.addVehicle(newVehicle, customerId);
    }

}