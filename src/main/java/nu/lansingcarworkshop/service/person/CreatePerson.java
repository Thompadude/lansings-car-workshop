package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreatePerson {

    public void createPerson(Person person) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(person);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}