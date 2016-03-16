package nu.lansingcarworkshop.service.servicetask;

import nu.lansingcarworkshop.entity.person.Employee;
import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.servicetask.ServiceTask;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateServiceTask {

    public void createServiceTask(ServiceTask serviceTask, Vehicle vehicle, Person person) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(serviceTask);

        setVehicleInService(serviceTask, vehicle.getId(), entityManager);

        setResponsibleEmployee(serviceTask, person.getId(), entityManager);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void setResponsibleEmployee(ServiceTask serviceTask, int personId, EntityManager entityManager) {
        Person responsibleEmployee = entityManager.find(Person.class, personId);
        serviceTask.setResponsibleEmployee(((Employee) responsibleEmployee));
    }

    private void setVehicleInService(ServiceTask serviceTask, int vehicleId, EntityManager entityManager) {
        Vehicle vehicleInService = entityManager.find(Vehicle.class, vehicleId);
        serviceTask.setVehicle(vehicleInService);
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}
