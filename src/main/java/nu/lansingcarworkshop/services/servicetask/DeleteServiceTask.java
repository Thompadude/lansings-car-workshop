package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import java.util.List;

public class DeleteServiceTask {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    private ReadServiceTask readServiceTask = new ReadServiceTask();
    private List serviceTasks;

    public void deleteServiceTaskById(int serviceTaskId) {
        entityManagerCoordinator.beginTransaction();

        ServiceTask serviceTask = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTaskId);

        entityManagerCoordinator.getEntityManager().remove(serviceTask);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void deleteAllServiceTasksFromCustomer(int customerId) {
        entityManagerCoordinator.beginTransaction();

        serviceTasks = readServiceTask.getAllServiceTasksByCustomerId(customerId);

        //noinspection unchecked
        removeListOfServiceTasks(serviceTasks);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void deleteAllServiceTasksFromVehicle(int vehicleId) {
        entityManagerCoordinator.beginTransaction();

        serviceTasks = readServiceTask.getAllServiceTasksByVehicleId(vehicleId);

        //noinspection unchecked
        removeListOfServiceTasks(serviceTasks);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void removeListOfServiceTasks(List<ServiceTask> serviceTasks) {
        for (ServiceTask serviceTask : serviceTasks) {
            ServiceTask serviceTaskToDelete = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTask.getId());
            entityManagerCoordinator.getEntityManager().remove(serviceTaskToDelete);
        }
    }

}