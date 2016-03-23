package nu.lansingcarworkshop.servlets.servicetask;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.person.ReadPerson;
import nu.lansingcarworkshop.services.servicetask.CreateServiceTask;
import nu.lansingcarworkshop.services.vehicle.ReadVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "CreateServiceTaskServlet")
public class CreateServiceTaskServlet extends HttpServlet {

    private LocalDateTime time;
    private String note;
    private Employee responsibleEmployee;
    private Vehicle vehicleInService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        initializeVariablesFromPostRequest(request);

        ServiceTask serviceTask = new ServiceTask(time, note, responsibleEmployee, vehicleInService);
        CreateServiceTask createServiceTask = new CreateServiceTask();
        createServiceTask.createServiceTask(serviceTask, vehicleInService, responsibleEmployee);

        response.getWriter().print("Service task booked.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        time = LocalDateTime.parse(request.getParameter("service-time"));
        note = request.getParameter("service-note");
        initializeEmployee(request);
        initializeVehicle(request);
    }

    private void initializeEmployee(HttpServletRequest request) {
        ReadPerson readPerson = new ReadPerson();
        responsibleEmployee = (Employee) readPerson.getPersonById(Integer.parseInt(request.getParameter("employee-id")));
    }

    private void initializeVehicle(HttpServletRequest request) {
        ReadVehicle readVehicle = new ReadVehicle();
        vehicleInService = readVehicle.getVehicleById(Integer.parseInt(request.getParameter("vehicle-id")));
    }

}
