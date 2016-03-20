package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

public class UpdateServiceTask {

    private ServiceTask serviceTaskWithUpdatedAttributes;
    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    /**
     * Fetch the service task to update from the database and use the temp service task's attributes for the update.
     * The temp service task have the same ID number as the service task in the database.
     *
     * @param serviceTaskWithUpdatedAttributes is the temp service task. Is used as the placeholder for the new attributes.
     */
    public void updateServiceTask(ServiceTask serviceTaskWithUpdatedAttributes) {
        this.serviceTaskWithUpdatedAttributes = serviceTaskWithUpdatedAttributes;

        entityManagerCoordinator.beginTransaction();

        ServiceTask serviceTaskToUpdate = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTaskWithUpdatedAttributes.getId());
        setNewAttributes(serviceTaskToUpdate);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void toggleServiceTaskCompletion(int serviceId, boolean isServiceCompleted) {
        entityManagerCoordinator.beginTransaction();

        ServiceTask serviceTask = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceId);

        checkIfServiceTaskIsCompleted(isServiceCompleted, serviceTask);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void checkIfServiceTaskIsCompleted(boolean isServiceCompleted, ServiceTask serviceTask) {
        if (isServiceCompleted) {
            serviceTask.setCompleted(true);
        } else {
            serviceTask.setCompleted(false);
        }
    }

    private void setNewAttributes(ServiceTask serviceTaskToUpdate) {
        serviceTaskToUpdate.setResponsibleEmployee(serviceTaskWithUpdatedAttributes.getResponsibleEmployee());
        serviceTaskToUpdate.setVehicle(serviceTaskWithUpdatedAttributes.getVehicle());
        serviceTaskToUpdate.setNote(serviceTaskWithUpdatedAttributes.getNote());
        serviceTaskToUpdate.setTime(serviceTaskWithUpdatedAttributes.getTime());
    }

}