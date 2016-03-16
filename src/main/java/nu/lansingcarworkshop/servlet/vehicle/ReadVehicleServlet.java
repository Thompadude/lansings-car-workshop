package nu.lansingcarworkshop.servlet.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.vehicle.ReadVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReadVehicleServlet")
public class ReadVehicleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vehicle vehicle = getVehicleByParameter(request);

        getServletContext().setAttribute("currentVehicle", vehicle);
        response.sendRedirect("vehicle/vehicle-profile.jsp");
    }

    private Vehicle getVehicleByParameter(HttpServletRequest request) {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        ReadVehicle readVehicle = new ReadVehicle();
        return readVehicle.getVehicleById(vehicleId);
    }

}