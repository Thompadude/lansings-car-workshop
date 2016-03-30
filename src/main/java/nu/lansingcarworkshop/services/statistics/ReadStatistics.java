package nu.lansingcarworkshop.services.statistics;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.Query;
import java.util.List;

public class ReadStatistics {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public Long getNumberOfCompletedServiceTasks() {
        return (Long) entityManagerCoordinator.getEntityManager().createQuery("SELECT COUNT(s.isCompleted) FROM ServiceTask s WHERE s.isCompleted = true").getSingleResult();
    }

    public int getNumberOfUniqueCustomersWithServiceBookings() {
        List listOfUniqueCustomersWithServiceBookings = entityManagerCoordinator.getEntityManager().createQuery("SELECT DISTINCT(s.customer) FROM ServiceTask s").getResultList();
        return listOfUniqueCustomersWithServiceBookings.size();
    }

    public Customer getCustomerWithTheMostServiceBookings() {
        Query query = entityManagerCoordinator.getEntityManager().createQuery("SELECT COUNT(s.customer.id), s.customer FROM ServiceTask s GROUP BY s.customer.id");

        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.getResultList();

        return findTheCustomerWithTheMostServiceBookings(queryResults);
    }

    private Customer findTheCustomerWithTheMostServiceBookings(List<Object[]> queryResults) {
        Long amountOfServiceBookings = -1L, amountOfServiceBookingsForSpecificCustomer;
        Customer customerWithTheMostServiceBookings = null;

        for (Object[] queryResult : queryResults) {
            amountOfServiceBookingsForSpecificCustomer = ((Number) queryResult[0]).longValue();

            if (amountOfServiceBookingsForSpecificCustomer > amountOfServiceBookings) {
                amountOfServiceBookings = amountOfServiceBookingsForSpecificCustomer;
                customerWithTheMostServiceBookings = (Customer) queryResult[1];
            }

        }
        return customerWithTheMostServiceBookings;
    }

}