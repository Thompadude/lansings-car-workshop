package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.services.vehicle.DeleteVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteVehicleServlet", urlPatterns = "/DeleteVehicleServlet")
public class DeleteVehicleServlet extends HttpServlet {

    private boolean isDeleteVehicleInquired;
    private int vehicleId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initializeVariablesFromGetRequest(request);

        boolean isAdminLoggedIn = (boolean) request.getSession().getAttribute("isAdminLoggedIn");
        if (isDeleteVehicleInquired && isAdminLoggedIn) {
            deleteVehicle(vehicleId);
        }

        response.sendRedirect("/ReadVehicleServlet?&action=list-vehicles");
    }

    private void initializeVariablesFromGetRequest(HttpServletRequest request) {
        isDeleteVehicleInquired = Boolean.parseBoolean(request.getParameter("deleteVehicle"));
        vehicleId = Integer.parseInt(request.getParameter("vehicle-id"));
    }

    private void deleteVehicle(int vehicleId) {
        DeleteVehicle deleteVehicle = new DeleteVehicle();
        deleteVehicle.deleteVehicleById(vehicleId);
    }

}
