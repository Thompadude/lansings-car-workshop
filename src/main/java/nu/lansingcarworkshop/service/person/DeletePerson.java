package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;
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

        DeleteVehicle deleteVehicle = new DeleteVehicle();
        deleteVehicle.deleteAllVehiclesFromCustomer(person);

        entityManager.remove(person);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}