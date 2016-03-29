package nu.lansingcarworkshop.services.statistics;

import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

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

}