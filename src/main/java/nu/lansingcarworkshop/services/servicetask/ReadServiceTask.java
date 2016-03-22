package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.Query;
import java.util.List;

public class ReadServiceTask {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    private Query queryResponse;

    public List getAllServiceTasks() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s ORDER BY s.time").getResultList();
    }

    public List getTodaysServiceTasks() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('DAY', s.time) = FUNCTION('DAY', CURRENT_DATE)").getResultList();
    }

    public List getThisMonthsServiceTasks() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('MONTH', s.time) = FUNCTION('MONTH', CURRENT_DATE)").getResultList();
    }

    public List getNextMonthsServiceTasks() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('MONTH', s.time) = (FUNCTION('MONTH', CURRENT_DATE) + 1)").getResultList();
    }

    public ServiceTask getServiceTaskById(int serviceTaskId) {
        return entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTaskId);
    }

    public List getAllServiceTasksByCarId(int vehicleId) {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.vehicle.id = :id");
        queryResponse.setParameter("id", vehicleId);
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByEmployeeId(int employeeId) {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.responsibleEmployee.id = :id");
        queryResponse.setParameter("id", employeeId);
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByCustomerId(int customerId) {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.customer.id = :id");
        queryResponse.setParameter("id", customerId);
        return queryResponse.getResultList();
    }

}