package nu.lansingcarworkshop.servlets.helpers.setcontext;

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

}