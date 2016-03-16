package nu.lansingcarworkshop.service.servicetask;

import nu.lansingcarworkshop.entity.servicetask.ServiceTask;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateServiceTask {

    private ServiceTask serviceTaskWithUpdatedAttributes;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    /**
     * Fetch the service task to update from the database and use the temp service task's attributes for the update.
     * The temp service task have the same ID number as the service task in the database.
     *
     * @param serviceTaskWithUpdatedAttributes is the temp service task. Is used as the placeholder for the new attributes.
     */
    public void updateServiceTask(ServiceTask serviceTaskWithUpdatedAttributes) {
        this.serviceTaskWithUpdatedAttributes = serviceTaskWithUpdatedAttributes;

        entityManager.getTransaction().begin();

        ServiceTask serviceTaskToUpdate = entityManager.find(ServiceTask.class, serviceTaskWithUpdatedAttributes.getId());
        setNewAttributes(serviceTaskToUpdate);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    public void toggleServiceTaskCompletion(int serviceId, boolean isServiceCompleted) {
        entityManager.getTransaction().begin();

        ServiceTask serviceTask = entityManager.find(ServiceTask.class, serviceId);

        if (isServiceCompleted) {
            serviceTask.setCompleted(true);
        } else {
            serviceTask.setCompleted(false);
        }

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void setNewAttributes(ServiceTask serviceTaskToUpdate) {
        serviceTaskToUpdate.setResponsibleEmployee(serviceTaskWithUpdatedAttributes.getResponsibleEmployee());
        serviceTaskToUpdate.setVehicle(serviceTaskWithUpdatedAttributes.getVehicle());
        serviceTaskToUpdate.setNote(serviceTaskWithUpdatedAttributes.getNote());
        serviceTaskToUpdate.setTime(serviceTaskWithUpdatedAttributes.getTime());
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}