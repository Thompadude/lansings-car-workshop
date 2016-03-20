package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

public class UpdatePerson {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    private Person personWithUpdatedAttributes;

    /**
     * Fetch the person to update from the database and use the temp person's attributes for the update.
     * The temp person have the same ID number as the person in the database.
     *
     * @param personWithUpdatedAttributes is the temp person. Is used as the placeholder for the new attributes.
     */
    public void updatePerson(Person personWithUpdatedAttributes) {
        this.personWithUpdatedAttributes = personWithUpdatedAttributes;
        entityManagerCoordinator.beginTransaction();

        Person personToUpdate = entityManagerCoordinator.getEntityManager().find(Person.class, personWithUpdatedAttributes.getId());
        setNewAttributes(personToUpdate);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void setNewAttributes(Person personToUpdate) {
        personToUpdate.setName(personWithUpdatedAttributes.getName());
        personToUpdate.setContactInformation(personWithUpdatedAttributes.getContactInformation());
        personToUpdate.setBirthdate(personWithUpdatedAttributes.getBirthdate());
        personToUpdate.setSex(personWithUpdatedAttributes.getSex());
    }

}