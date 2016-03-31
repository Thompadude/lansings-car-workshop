package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

public class UpdateServiceTask {

    private ServiceTask serviceTaskWithUpdatedAttributes;
    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    /**
     * Fetch the service task to update from the database and use the temp service task's attributes for the update.
     * The temp service task have the same ID number as the service task in the database.
     *
     * @param serviceTaskWithUpdatedAttributes is the temp service task. Is used as the placeholder for the new attributes.
     */
    public void updateServiceTask(ServiceTask serviceTaskWithUpdatedAttributes) {
        this.serviceTaskWithUpdatedAttributes = serviceTaskWithUpdatedAttributes;

        entityManagerFacade.beginTransaction();

        ServiceTask serviceTaskToUpdate = entityManagerFacade.getEntityManager().find(ServiceTask.class, serviceTaskWithUpdatedAttributes.getId());
        setNewAttributes(serviceTaskToUpdate);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    private void setNewAttributes(ServiceTask serviceTaskToUpdate) {
        serviceTaskToUpdate.setResponsibleEmployee(serviceTaskWithUpdatedAttributes.getResponsibleEmployee());
        serviceTaskToUpdate.setVehicle(serviceTaskWithUpdatedAttributes.getVehicle());
        serviceTaskToUpdate.setNote(serviceTaskWithUpdatedAttributes.getNote());
        serviceTaskToUpdate.setTime(serviceTaskWithUpdatedAttributes.getTime());
        serviceTaskToUpdate.setCompleted(serviceTaskWithUpdatedAttributes.isCompleted());
    }

}