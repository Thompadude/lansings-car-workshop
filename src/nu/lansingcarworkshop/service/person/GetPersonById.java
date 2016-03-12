package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GetPersonById {

    public Person getPersonById(int personId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Person.class, personId);
    }

}