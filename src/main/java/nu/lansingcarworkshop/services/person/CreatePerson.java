package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

public class CreatePerson {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void createPerson(Person person) {
        entityManagerCoordinator.beginTransactionSaveEntityAndCommit(person);
    }

}