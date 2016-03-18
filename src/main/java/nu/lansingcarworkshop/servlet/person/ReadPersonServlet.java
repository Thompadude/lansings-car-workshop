package nu.lansingcarworkshop.servlet.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.person.ReadPerson;
import nu.lansingcarworkshop.service.vehicle.ReadVehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadPersonServlet")
public class ReadPersonServlet extends HttpServlet {

    boolean hasAddVehicleBeenRequested;
    boolean hasPersonListBeenRequested;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hasAddVehicleBeenRequested = Boolean.parseBoolean(request.getParameter("hasAddVehicleBeenRequested"));
        hasPersonListBeenRequested = Boolean.parseBoolean(request.getParameter("hasPersonListBeenRequested"));

        Person currentPerson = getPersonByParameter(request);

        setServletAttributes(currentPerson);

        redirectToCorrectJsp(request, response);
    }

    private Person getPersonByParameter(HttpServletRequest request) {
        if (!hasPersonListBeenRequested) {
            int personId = Integer.parseInt(request.getParameter("personId"));
            ReadPerson readPerson = new ReadPerson();
            return readPerson.getPersonById(personId);
        } else {
            return null;
        }
    }

    private void setServletAttributes(Person currentPerson) {
        if (!hasPersonListBeenRequested) {
            getServletContext().setAttribute("currentPerson", currentPerson);
            setCurrentPersonsVehiclesServletAttribute(currentPerson);
        } else {
            setPersonListServletAttribute();
        }
    }

    private void setCurrentPersonsVehiclesServletAttribute(Person currentPerson) {
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllCarsByCustomerId(currentPerson.getId());
        getServletContext().setAttribute("currentPersonsVehicles", vehicles);
    }

    private void setPersonListServletAttribute() {
        setEmployeeListServletAttribute();
        setCustomerListServletAttribute();
    }

    private void setCustomerListServletAttribute() {
        ReadPerson readPerson = new ReadPerson();
        List customers = readPerson.getAllCustomers();
        getServletContext().setAttribute("listOfCustomers", customers);
    }

    private void setEmployeeListServletAttribute() {
        ReadPerson getEmployees = new ReadPerson();
        List employees = getEmployees.getAllEmployees();
        getServletContext().setAttribute("listOfEmployees", employees);
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (hasAddVehicleBeenRequested) {
            response.sendRedirect(request.getContextPath() + "/person/person-addvehicle.jsp");
        } else if (hasPersonListBeenRequested) {
            response.sendRedirect(request.getContextPath() + "/person/persons-edit-delete.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "person/person-profile.jsp");
        }
    }

}