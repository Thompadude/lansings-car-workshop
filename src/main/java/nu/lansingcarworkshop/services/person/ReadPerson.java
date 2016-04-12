package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.Query;
import java.util.List;

public class ReadPerson {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();
    private Query queryResponse;

    public Person getPersonById(int personId) {
        return entityManagerFacade.getEntityManager().find(Person.class, personId);
    }

    public List getAllCustomers() {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT c FROM Customer c ORDER BY c.name");
        return queryResponse.getResultList();
    }

    public List getAllEmployees() {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT e FROM Employee e ORDER BY e.name");
        return queryResponse.getResultList();
    }

    public List getAllPersons() {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT p FROM Person p");
        return queryResponse.getResultList();
    }

}