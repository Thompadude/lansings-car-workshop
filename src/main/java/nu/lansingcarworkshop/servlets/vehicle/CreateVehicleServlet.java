package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.models.vehicle.Car;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.vehicle.CreateVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "CreateVehicleServlet", urlPatterns = "/CreateVehicleServlet")
public class CreateVehicleServlet extends HttpServlet {

    private String registrationPlate, make, fuel;
    private LocalDate modelYear;
    private int customerId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        initializeVariablesFromPostRequest(request);

        createAndAddCarToDatabase();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        registrationPlate = request.getParameter("vehicle-registration-plate").toUpperCase();
        make = request.getParameter("vehicle-make");
        fuel = request.getParameter("vehicle-fuel-type");
        modelYear = LocalDate.parse(request.getParameter("vehicle-model-year"));
        customerId = Integer.parseInt(request.getParameter("customer-id"));
    }

    private void createAndAddCarToDatabase() {
        //TODO (optional) add more methods for other types of vehicles.
        Vehicle newVehicle = new Car(registrationPlate, make, modelYear, fuel);
        CreateVehicle createVehicle = new CreateVehicle();
        createVehicle.createVehicle(newVehicle, customerId);
    }

}