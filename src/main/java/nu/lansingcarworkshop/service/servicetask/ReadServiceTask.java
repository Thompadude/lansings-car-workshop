package nu.lansingcarworkshop.service.servicetask;

import nu.lansingcarworkshop.entity.servicetask.ServiceTask;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ReadServiceTask {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List getAllServiceTasks() {
        Query queryServiceTasks = entityManager.createQuery("SELECT s FROM ServiceTask s ORDER BY s.time");
        return queryServiceTasks.getResultList();
    }

    public ServiceTask getServiceTaskById(int serviceTaskId) {
        return entityManager.find(ServiceTask.class, serviceTaskId);
    }

    public List getAllServiceTaskByCarId(Vehicle vehicle) {
        //TODO add more methods for other types of vehicles.
        Query query = entityManager.createQuery("SELECT s FROM ServiceTask s JOIN Car c WHERE c.id LIKE :id");
        query.setParameter("id", vehicle.getId());

        List serviceTasks = query.getResultList();
        return serviceTasks;
    }

    //TODO create method for reading all service tasks based on employee id.

}