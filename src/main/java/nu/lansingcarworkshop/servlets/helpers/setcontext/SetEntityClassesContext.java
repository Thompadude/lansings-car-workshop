package nu.lansingcarworkshop.servlets.helpers.setcontext;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.person.ReadPerson;
import nu.lansingcarworkshop.services.servicetask.ReadServiceTask;
import nu.lansingcarworkshop.services.vehicle.ReadVehicle;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * All methods return true if the context attributes is set successfully, else false.
 */
public class SetEntityClassesContext {

    public boolean setCurrentPerson(ServletContext servletContext, String personId) {
        Person currentPerson;

        if (personId != null) {
            if (personId.equals("")) {
                return false;
            }
            ReadPerson readPerson = new ReadPerson();
            currentPerson = readPerson.getPersonById(Integer.parseInt(personId));
        } else {
            return false;
        }

        if (currentPerson != null) {
            servletContext.setAttribute("currentPerson", currentPerson);
            return setCurrentPersonsVehicles(servletContext, currentPerson);
        }
        return false;
    }

    public boolean setPersonList(ServletContext servletContext) {
        ReadPerson readPerson = new ReadPerson();
        List persons = readPerson.getAllPersons();

        if (persons != null) {
            servletContext.setAttribute("listOfPersons", persons);
            return true;
        }
        return false;
    }

    public boolean setCustomerList(ServletContext servletContext) {
        ReadPerson readPerson = new ReadPerson();
        List customers = readPerson.getAllCustomers();

        if (customers != null) {
            servletContext.setAttribute("listOfCustomers", customers);
            return true;
        }
        return false;
    }

    public boolean setEmployeeList(ServletContext servletContext) {
        ReadPerson readPerson = new ReadPerson();
        List employees = readPerson.getAllEmployees();

        if (employees != null) {
            servletContext.setAttribute("listOfEmployees", employees);
            return true;
        }
        return false;
    }

    public boolean setCurrentVehicle(ServletContext servletContext, String vehicleId) {
        Vehicle currentVehicle;

        if (vehicleId != null) {
            ReadVehicle readVehicle = new ReadVehicle();
            currentVehicle = readVehicle.getVehicleById(Integer.parseInt(vehicleId));
        } else {
            return false;
        }

        if (currentVehicle != null) {
            servletContext.setAttribute("currentVehicle", currentVehicle);
            return true;
        }
        return false;
    }

    public boolean setVehicleList(ServletContext servletContext) {
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllVehicles();

        if (vehicles != null) {
            servletContext.setAttribute("listOfVehicles", vehicles);
            return true;
        }
        return false;
    }

    public boolean setServiceTasksLists(ServletContext servletContext) {
        ReadServiceTask readServiceTask = new ReadServiceTask();
        List allServiceTasks = readServiceTask.getAllServiceTasks();

        if (allServiceTasks != null) {
            servletContext.setAttribute("listOfServiceTasks", allServiceTasks);
            return true;
        }
        return false;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public boolean setUpcomingServiceTasksLists(ServletContext servletContext) {
        ReadServiceTask readServiceTask = new ReadServiceTask();
        List todaysServiceTasks = readServiceTask.getTodaysServiceTasks();
        List nextMonthsServiceTasks = readServiceTask.getNextMonthsServiceTasks();

        if (todaysServiceTasks != null && nextMonthsServiceTasks != null) {
            servletContext.setAttribute("listOfTodaysServiceTasks", todaysServiceTasks);
            servletContext.setAttribute("listOfNextMonthsServiceTasks", nextMonthsServiceTasks);
            return true;
        }
        return false;
    }

    public boolean setServiceTasksListByVehicle(ServletContext servletContext, String vehicleId) {
        ReadServiceTask readServiceTask = new ReadServiceTask();
        List serviceTasks = readServiceTask.getAllServiceTasksByVehicleId(Integer.parseInt(vehicleId));

        if (serviceTasks != null) {
            servletContext.setAttribute("listOfServiceTasks", serviceTasks);
            return true;
        }
        return false;
    }

    public boolean setCurrentServiceTask(ServletContext servletContext, String serviceTaskId) {
        ServiceTask currentServiceTask;

        if (serviceTaskId != null) {
            ReadServiceTask readServiceTask = new ReadServiceTask();
            currentServiceTask = readServiceTask.getServiceTaskById(Integer.parseInt(serviceTaskId));
        } else {
            return false;
        }

        if (currentServiceTask != null) {
            servletContext.setAttribute("currentServiceTask", currentServiceTask);
            return true;
        }
        return false;
    }

    private boolean setCurrentPersonsVehicles(ServletContext servletContext, Person currentPerson) {
        ReadVehicle readVehicle = new ReadVehicle();
        List vehicles = readVehicle.getAllVehiclesByCustomerId(currentPerson.getId());
        servletContext.setAttribute("currentPersonsVehicles", vehicles);

        return servletContext.getAttribute("currentPersonsVehicles") != null;
    }

}