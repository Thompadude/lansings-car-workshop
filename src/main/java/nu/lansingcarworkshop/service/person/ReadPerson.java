package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

import javax.persistence.Query;
import java.util.List;

public class ReadPerson {

    EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    Query queryResponse;

    public Person getPersonById(int personId) {
        return entityManagerCoordinator.getEntityManager().find(Person.class, personId);
    }

    public List getAllCustomers() {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT c FROM Customer c ORDER BY c.name");
        return queryResponse.getResultList();
    }

    public List getAllEmployees() {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT e FROM Employee e ORDER BY e.name");
        return queryResponse.getResultList();
    }

    public List getAllPersons() {
        queryResponse = entityManagerCoordinator.getEntityManager().createQuery("SELECT p FROM Person p");
        return queryResponse.getResultList();
    }

}