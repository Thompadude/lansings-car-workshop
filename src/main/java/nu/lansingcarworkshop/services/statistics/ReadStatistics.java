package nu.lansingcarworkshop.services.statistics;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ReadStatistics {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public Long getNumberOfCompletedServiceTasks() {
        return (Long) entityManagerFacade.getEntityManager().createQuery("SELECT COUNT(s.isCompleted) FROM ServiceTask s WHERE s.isCompleted = true").getSingleResult();
    }

    public int getNumberOfUniqueCustomersWithServiceBookings() {
        List listOfUniqueCustomersWithServiceBookings = entityManagerFacade.getEntityManager().createQuery("SELECT DISTINCT(s.customer) FROM ServiceTask s").getResultList();
        return listOfUniqueCustomersWithServiceBookings.size();
    }

    public List<Customer> getCustomersWithTheMostServiceBookings() {
        Query query = entityManagerFacade.getEntityManager().createQuery("SELECT COUNT(s.customer.id), s.customer FROM ServiceTask s GROUP BY s.customer.id");

        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.getResultList();

        return findTheCustomersWithTheMostServiceBookings(queryResults);
    }

    private List<Customer> findTheCustomersWithTheMostServiceBookings(List<Object[]> queryResults) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Long mostAmountOfServiceBookings = getTheHighestAmountOfServiceBookings(queryResults);

        return populateAndGetListOfTheCustomersWithTheMostServiceBookings(queryResults, mostAmountOfServiceBookings);
    }

    private Long getTheHighestAmountOfServiceBookings(List<Object[]> queryResults) {
        Long mostAmountOfServiceBookings = -1L;
        Long amountOfServiceBookingsForSpecificCustomer;
        for (Object[] queryResult : queryResults) {
            amountOfServiceBookingsForSpecificCustomer = ((Number) queryResult[0]).longValue();

            if (amountOfServiceBookingsForSpecificCustomer > mostAmountOfServiceBookings) {
                mostAmountOfServiceBookings = amountOfServiceBookingsForSpecificCustomer;
            }
        }
        return mostAmountOfServiceBookings;
    }

    private List<Customer> populateAndGetListOfTheCustomersWithTheMostServiceBookings(List<Object[]> queryResults, Long mostAmountOfServiceBookings) {
        List<Customer> customersWithTheMostServiceBookings = new ArrayList<>();
        Long amountOfServiceBookingsForSpecificCustomer;
        for (Object[] queryResult : queryResults) {
            amountOfServiceBookingsForSpecificCustomer = ((Number) queryResult[0]).longValue();
            if (mostAmountOfServiceBookings.equals(amountOfServiceBookingsForSpecificCustomer)) {
                customersWithTheMostServiceBookings.add((Customer) queryResult[1]);
            }
        }
        return customersWithTheMostServiceBookings;
    }

}