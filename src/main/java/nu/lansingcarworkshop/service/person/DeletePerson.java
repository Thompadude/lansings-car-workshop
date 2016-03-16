package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Customer;
import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.servicetask.DeleteServiceTask;
import nu.lansingcarworkshop.service.vehicle.DeleteVehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeletePerson {

    public void deletePersonById(int personId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = entityManager.find(Person.class, personId);

        removeSubObjects(personId, person);

        entityManager.remove(person);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void removeSubObjects(int personId, Person person) {
        if (person instanceof Customer) {
            DeleteVehicle deleteVehicle = new DeleteVehicle();
            deleteVehicle.deleteAllVehiclesFromCustomer(person);
        } else {
            DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
            deleteServiceTask.removeEmployeeFromServiceTask(personId);
        }
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}