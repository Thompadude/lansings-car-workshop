package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

public class UpdatePerson {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();
    private Person personWithUpdatedAttributes;

    /**
     * Fetch the person to update from the database and use the temp person's attributes for the update.
     * The temp person have the same ID number as the person in the database.
     *
     * @param personWithUpdatedAttributes is the temp person. Is used as the placeholder for the new attributes.
     */
    public void updatePerson(Person personWithUpdatedAttributes) {
        this.personWithUpdatedAttributes = personWithUpdatedAttributes;
        entityManagerFacade.beginTransaction();

        Person personToUpdate = entityManagerFacade.getEntityManager().find(Person.class, personWithUpdatedAttributes.getId());
        setNewAttributes(personToUpdate);

        entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun();
    }

    private void setNewAttributes(Person personToUpdate) {
        personToUpdate.setName(personWithUpdatedAttributes.getName());
        personToUpdate.setContactInformation(personWithUpdatedAttributes.getContactInformation());
        personToUpdate.setBirthdate(personWithUpdatedAttributes.getBirthdate());
        personToUpdate.setSex(personWithUpdatedAttributes.getSex());

        if (personToUpdate instanceof Employee) {
            ((Employee) personToUpdate).setRole(((Employee) personWithUpdatedAttributes).getRole());
        }
    }

}