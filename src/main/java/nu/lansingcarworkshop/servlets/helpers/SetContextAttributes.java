package nu.lansingcarworkshop.servlets.helpers;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.person.ReadPerson;
import nu.lansingcarworkshop.services.servicetask.ReadServiceTask;
import nu.lansingcarworkshop.services.vehicle.ReadVehicle;

import javax.servlet.ServletContext;
import java.util.List;

public class SetContextAttributes {

    public boolean setCurrentPerson(ServletContext servletContext, String personId) {
        boolean isAttributeSetSuccessfully = false;

        Person currentPerson = null;

        if (personId != null) {
            ReadPerson readPerson = new ReadPerson();
            currentPerson = readPerson.getPersonById(Integer.parseInt(personId));
        }

        if (currentPerson != null) {
            servletContext.setAttribute("currentPerson", currentPerson);
            isAttributeSetSuccessfully = setCurrentPersonsVehicles(servletContext, currentPerson);
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setPersonList(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully;

        isAttributeSetSuccessfully = setEmployeeList(servletContext);

        if (isAttributeSetSuccessfully) {
            isAttributeSetSuccessfully = setCustomerList(servletContext);
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setCustomerList(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully = false;

        ReadPerson readPerson = new ReadPerson();
        List customers = readPerson.getAllCustomers();

        if (customers != null) {
            servletContext.setAttribute("listOfCustomers", customers);
            isAttributeSetSuccessfully = true;
        }
        return isAttributeSetSuccessfully;

    }

    public boolean setEmployeeList(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully = false;

        ReadPerson getEmployees = new ReadPerson();
        List employees = getEmployees.getAllEmployees();

        if (employees != null) {
            servletContext.setAttribute("listOfEmployees", employees);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setCurrentVehicle(ServletContext servletContext, String vehicleId) {
        boolean isAttributeSetSuccessfully = false;

        Vehicle currentVehicle = null;

        if (vehicleId != null) {
            ReadVehicle readVehicle = new ReadVehicle();
            currentVehicle = readVehicle.getVehicleById(Integer.parseInt(vehicleId));
        }

        if (currentVehicle != null) {
            servletContext.setAttribute("currentVehicle", currentVehicle);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setVehicleList(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully = false;

        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllCars();

        if (vehicles != null) {
            servletContext.setAttribute("listOfVehicles", vehicles);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setServiceTasksLists(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully = false;

        ReadServiceTask readServiceTask = new ReadServiceTask();

        List allServiceTasks = readServiceTask.getAllServiceTasks();

        if (allServiceTasks != null) {
            servletContext.setAttribute("listOfServiceTasks", allServiceTasks);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setUpcomingServiceTasksLists(ServletContext servletContext) {
        boolean isAttributeSetSuccessfully = false;

        ReadServiceTask readServiceTask = new ReadServiceTask();

        List todaysServiceTasks = readServiceTask.getTodaysServiceTasks();
        List nextMonthsServiceTasks = readServiceTask.getNextMonthsServiceTasks();

        if (todaysServiceTasks != null && nextMonthsServiceTasks != null) {
            servletContext.setAttribute("listOfTodaysServiceTasks", todaysServiceTasks);
            servletContext.setAttribute("listOfNextMonthsServiceTasks", nextMonthsServiceTasks);
            isAttributeSetSuccessfully = true;
        }
        return isAttributeSetSuccessfully;
    }

    public boolean setServiceTasksListByVehicle(ServletContext servletContext, String vehicleId) {
        boolean isAttributeSetSuccessfully = false;

        ReadServiceTask readServiceTask = new ReadServiceTask();
        List serviceTasks = readServiceTask.getAllServiceTasksByCarId(Integer.parseInt(vehicleId));

        if (serviceTasks != null) {
            servletContext.setAttribute("listOfServiceTasks", serviceTasks);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    public boolean setCurrentServiceTask(ServletContext servletContext, String serviceTaskId) {
        boolean isAttributeSetSuccessfully = false;

        ServiceTask currentServiceTask = null;

        if (serviceTaskId != null) {
            ReadServiceTask readServiceTask = new ReadServiceTask();
            currentServiceTask = readServiceTask.getServiceTaskById(Integer.parseInt(serviceTaskId));
        }

        if (currentServiceTask != null) {
            servletContext.setAttribute("currentServiceTask", currentServiceTask);
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }

    private boolean setCurrentPersonsVehicles(ServletContext servletContext, Person currentPerson) {
        boolean isAttributeSetSuccessfully = false;

        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllCarsByCustomerId(currentPerson.getId());
        servletContext.setAttribute("currentPersonsVehicles", vehicles);

        if (servletContext.getAttribute("currentPersonsVehicles") != null) {
            isAttributeSetSuccessfully = true;
        }

        return isAttributeSetSuccessfully;
    }
}