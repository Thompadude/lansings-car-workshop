package nu.lansingcarworkshop.servlet.vehicle;

import nu.lansingcarworkshop.service.vehicle.DeleteVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteVehicleServlet")
public class DeleteVehicleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isDeleteVehicleInquired = Boolean.parseBoolean(request.getParameter("deleteVehicle"));
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));

        if (isDeleteVehicleInquired) {
            DeleteVehicle deleteVehicle = new DeleteVehicle();
            deleteVehicle.deleteVehicleById(vehicleId);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
