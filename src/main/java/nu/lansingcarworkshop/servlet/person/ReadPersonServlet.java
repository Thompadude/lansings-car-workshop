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

    boolean isActionSuccessfullyExecuted;
    Person currentPerson;
    String action;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        action = request.getParameter("action");

        if (action.equalsIgnoreCase("listpersons")) {
            isActionSuccessfullyExecuted = setPersonListServletAttribute();
        } else {
            currentPerson = setCurrentPerson(request);
            isActionSuccessfullyExecuted = setCurrentPersonServletAttribute();
        }

        if (isActionSuccessfullyExecuted) {
            redirectToCorrectJsp(request, response);
        }
    }

    private Person setCurrentPerson(HttpServletRequest request) {
        String personIdString = request.getParameter("personId");

        if (personIdString != null) {
            int personId = Integer.parseInt(personIdString);
            ReadPerson readPerson = new ReadPerson();
            currentPerson = readPerson.getPersonById(personId);
        }
        return currentPerson;
    }

    private boolean setCurrentPersonServletAttribute() {
        if (currentPerson != null) {
            getServletContext().setAttribute("currentPerson", currentPerson);
            setCurrentPersonsVehiclesServletAttribute();
            return true;
        }
        return false;
    }

    private void setCurrentPersonsVehiclesServletAttribute() {
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllCarsByCustomerId(currentPerson.getId());
        getServletContext().setAttribute("currentPersonsVehicles", vehicles);
    }

    private boolean setPersonListServletAttribute() {
        isActionSuccessfullyExecuted = setEmployeeListServletAttribute();

        if (isActionSuccessfullyExecuted) {
            isActionSuccessfullyExecuted = setCustomerListServletAttribute();
        }
        return isActionSuccessfullyExecuted;
    }

    private boolean setCustomerListServletAttribute() {
        ReadPerson readPerson = new ReadPerson();
        List customers = readPerson.getAllCustomers();

        if (customers != null) {
            getServletContext().setAttribute("listOfCustomers", customers);
            return true;
        }
        return false;
    }

    private boolean setEmployeeListServletAttribute() {
        ReadPerson getEmployees = new ReadPerson();
        List employees = getEmployees.getAllEmployees();

        if (employees != null) {
            getServletContext().setAttribute("listOfEmployees", employees);
            return true;
        }
        return false;
    }

    private void redirectToCorrectJsp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (action) {
            case "addvehicle":
                response.sendRedirect(request.getContextPath() + "/person/person-addvehicle.jsp");
                break;
            case "listpersons":
                response.sendRedirect(request.getContextPath() + "/person/persons-edit-delete.jsp");
                break;
            case "viewprofile":
                response.sendRedirect(request.getContextPath() + "/person/person-profile.jsp");
                break;
            case "updateprofile":
                response.sendRedirect(request.getContextPath() + "/person/person-update.jsp");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "login.jsp");
        }
    }

}