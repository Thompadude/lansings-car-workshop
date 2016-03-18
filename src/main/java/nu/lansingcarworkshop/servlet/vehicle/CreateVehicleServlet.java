package nu.lansingcarworkshop.servlet.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Car;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.vehicle.CreateVehicle;

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

        createAndAddCarToDatabase();

        response.getWriter().print(" created.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        registrationPlate = request.getParameter("vehicle-registrationplate").toUpperCase();
        make = request.getParameter("vehicle-make");
        fuel = request.getParameter("vehicle-fueltype");
        modelYear = LocalDate.parse(request.getParameter("vehicle-modelyear"));
        customerId = Integer.parseInt(request.getParameter("customerId"));
    }

    private void createAndAddCarToDatabase() {
        //TODO add more methods for other types of vehicles.
        newVehicle = new Car(registrationPlate, make, modelYear, fuel);
        CreateVehicle createVehicle = new CreateVehicle();
        createVehicle.createVehicle(newVehicle, customerId);
    }

}