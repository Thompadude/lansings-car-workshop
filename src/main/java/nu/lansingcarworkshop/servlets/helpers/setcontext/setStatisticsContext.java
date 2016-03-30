package nu.lansingcarworkshop.servlets.helpers.setcontext;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.services.statistics.ReadStatistics;

import javax.servlet.ServletContext;

/**
 * All methods return true if the context attributes is set successfully, else false.
 */
public class SetStatisticsContext {

    public boolean setServiceTaskStatistics(ServletContext servletContext) {
        boolean isContextSetSuccessfully = setAmountOfCompletedServiceTasks(servletContext);
        if (isContextSetSuccessfully) {
            isContextSetSuccessfully = setNumberOfUniqueCustomersWithServiceBookings(servletContext);
        }
        if (isContextSetSuccessfully) {
            isContextSetSuccessfully = setCustomerWithTheMostServiceBookings(servletContext);
        }
        return isContextSetSuccessfully;
    }

    public boolean setAmountOfCompletedServiceTasks(ServletContext servletContext) {
        ReadStatistics readStatistics = new ReadStatistics();
        Long amountOfCompletedServiceTasks = readStatistics.getNumberOfCompletedServiceTasks();
        servletContext.setAttribute("amountOfCompletedServiceTasks", amountOfCompletedServiceTasks);

        return servletContext.getAttribute("amountOfCompletedServiceTasks") != null;
    }

    public boolean setNumberOfUniqueCustomersWithServiceBookings(ServletContext servletContext) {
        ReadStatistics readStatistics = new ReadStatistics();
        int numberOfUniqueCustomersWithServiceBookings = readStatistics.getNumberOfUniqueCustomersWithServiceBookings();
        servletContext.setAttribute("numberOfUniqueCustomersWithServiceBookings", numberOfUniqueCustomersWithServiceBookings);

        return servletContext.getAttribute("numberOfUniqueCustomersWithServiceBookings") != null;
    }

    public boolean setCustomerWithTheMostServiceBookings(ServletContext servletContext) {
        ReadStatistics readStatistics = new ReadStatistics();
        Customer customerWithTheMostServiceBookings = readStatistics.getCustomerWithTheMostServiceBookings();
        servletContext.setAttribute("customerWithTheMostServiceBookings", customerWithTheMostServiceBookings);

        return servletContext.getAttribute("customerWithTheMostServiceBookings") != null;
    }

}