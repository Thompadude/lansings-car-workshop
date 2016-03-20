package nu.lansingcarworkshop.servlets.servicetask;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.person.ReadPerson;
import nu.lansingcarworkshop.services.servicetask.ReadServiceTask;
import nu.lansingcarworkshop.services.servicetask.UpdateServiceTask;
import nu.lansingcarworkshop.services.vehicle.ReadVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "UpdateServiceTaskServlet")
public class UpdateServiceTaskServlet extends HttpServlet {

    private Employee responsibleEmployee;
    private LocalDateTime time;
    private ServiceTask serviceTaskWithUpdatedAttributes;
    private String note;
    private Vehicle vehicleInService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int serviceTaskId = Integer.parseInt(request.getParameter("servicetaskid"));
        ReadServiceTask readServiceTask = new ReadServiceTask();
        serviceTaskWithUpdatedAttributes = readServiceTask.getServiceTaskById(serviceTaskId);

        initializeVariablesFromPostRequest(request);

        setNewAttributes();

        UpdateServiceTask updateServiceTask = new UpdateServiceTask();
        updateServiceTask.updateServiceTask(serviceTaskWithUpdatedAttributes);

        response.sendRedirect("/ReadServiceTaskServlet?serviceTaskId=" + serviceTaskWithUpdatedAttributes.getId() + "&action=viewprofile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void initializeVariablesFromPostRequest(HttpServletRequest request) {
        time = LocalDateTime.parse(request.getParameter("service-time"));
        note = request.getParameter("service-note");
        initializeEmployee(request);
    }

    private void initializeEmployee(HttpServletRequest request) {
        ReadPerson readPerson = new ReadPerson();
        responsibleEmployee = (Employee) readPerson.getPersonById(Integer.parseInt(request.getParameter("employee-list")));
    }

    private void setNewAttributes() {
        serviceTaskWithUpdatedAttributes.setResponsibleEmployee(responsibleEmployee);
        serviceTaskWithUpdatedAttributes.setTime(time);
        serviceTaskWithUpdatedAttributes.setNote(note);
    }

}