package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

public class CreatePerson {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void createPerson(Person person) {
        entityManagerCoordinator.beginTransactionSaveEntityAndCommit(person);
    }

}