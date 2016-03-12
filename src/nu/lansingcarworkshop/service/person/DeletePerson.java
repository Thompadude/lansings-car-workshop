package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.vehicle.DeleteAllVehiclesFromCustomer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeletePerson {

    public void deletePerson(int personId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = entityManager.find(Person.class, personId);

        DeleteAllVehiclesFromCustomer deleteAllVehiclesFromCustomer = new DeleteAllVehiclesFromCustomer();
        deleteAllVehiclesFromCustomer.deleteAllVehiclesFromCustomer(person);

        entityManager.remove(person);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}