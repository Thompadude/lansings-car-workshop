package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.Query;
import java.util.List;

public class ReadServiceTask {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    private Query queryResponse;

    public List getAllServiceTasks() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s ORDER BY s.time").getResultList();
    }

    public ServiceTask getServiceTaskById(int serviceTaskId) {
        return entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTaskId);
    }

    public List getAllServiceTasksByCarId(Vehicle vehicle) {
        //TODO add more methods for other types of vehicles.
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s JOIN Car c WHERE c.id = :id");
        queryResponse.setParameter("id", vehicle.getId());
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByEmployeeId(int employeeId) {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s JOIN Employee e ON s.responsibleEmployee.id = e.id WHERE s.responsibleEmployee.id = :id");
        queryResponse.setParameter("id", employeeId);
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByCustomerId(int customerId) {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT s FROM ServiceTask s JOIN Customer c WHERE c.id = :id");
        queryResponse.setParameter("id", customerId);
        return queryResponse.getResultList();
    }
}