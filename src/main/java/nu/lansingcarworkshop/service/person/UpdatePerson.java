package nu.lansingcarworkshop.service.person;

import nu.lansingcarworkshop.entity.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdatePerson {

    private Person personWithUpdatedAttributes;

    /**
     * Fetch the person to update from the database and use the temp person's attributes for the update.
     * The temp person have the same ID number as the person in the database.
     *
     * @param personWithUpdatedAttributes is the temp person. Is used as the placeholder for the new attributes.
     */
    public void updatePerson(Person personWithUpdatedAttributes) {
        this.personWithUpdatedAttributes = personWithUpdatedAttributes;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person personToUpdate = entityManager.find(Person.class, personWithUpdatedAttributes.getId());
        setNewAttributes(personToUpdate);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void setNewAttributes(Person personToUpdate) {
        personToUpdate.setName(personWithUpdatedAttributes.getName());
        personToUpdate.setContactInformation(personWithUpdatedAttributes.getContactInformation());
        personToUpdate.setBirthdate(personWithUpdatedAttributes.getBirthdate());
        personToUpdate.setSex(personWithUpdatedAttributes.getSex());
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}