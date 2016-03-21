package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import java.util.List;

public class DeleteServiceTask {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void deleteServiceTaskById(int serviceTaskId) {
        entityManagerCoordinator.beginTransaction();

        ServiceTask serviceTask = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTaskId);

        entityManagerCoordinator.getEntityManager().remove(serviceTask);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void deleteAllServiceTasksFromCustomer(int customerId) {
        entityManagerCoordinator.beginTransaction();

        ReadServiceTask readServiceTask = new ReadServiceTask();
        @SuppressWarnings("unchecked")
        List<ServiceTask> serviceTasks = readServiceTask.getAllServiceTasksByCustomerId(customerId);

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