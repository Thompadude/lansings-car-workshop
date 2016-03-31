package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

public class CreatePerson {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public void createPerson(Person person) {
        entityManagerFacade.beginTransactionSaveEntityAndCommit(person);
    }

}