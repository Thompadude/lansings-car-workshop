package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ReadPerson {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Person getPersonById(int personId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Person.class, personId);
    }

    public List getAllCustomers() {
        Query queryCustomers = entityManager.createQuery("SELECT c FROM Customer c ORDER BY c.name");
        return queryCustomers.getResultList();
    }

    public List getAllEmployees() {
        Query queryEmployees = entityManager.createQuery("SELECT e FROM Employee e ORDER BY e.name");
        return queryEmployees.getResultList();
    }

    public List getAllPersons() {
        Query queryPersons = entityManager.createQuery("SELECT c, e FROM Customer c, Employee e");
        return queryPersons.getResultList();
    }

}