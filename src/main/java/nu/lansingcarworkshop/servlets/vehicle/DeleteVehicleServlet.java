package nu.lansingcarworkshop.servlets.vehicle;

import nu.lansingcarworkshop.services.vehicle.DeleteVehicle;

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

        boolean isAdminLoggedIn = (boolean) request.getSession().getAttribute("isAdminLoggedIn");
        if (isDeleteVehicleInquired && isAdminLoggedIn) {
            DeleteVehicle deleteVehicle = new DeleteVehicle();
            deleteVehicle.deleteVehicleById(vehicleId);
        }

        response.sendRedirect("/ReadVehicleServlet?&action=listvehicles");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
