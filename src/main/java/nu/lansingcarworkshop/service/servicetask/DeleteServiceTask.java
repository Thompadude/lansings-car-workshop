package nu.lansingcarworkshop.service.servicetask;

import nu.lansingcarworkshop.entity.servicetask.ServiceTask;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

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

        for (ServiceTask serviceTask : serviceTasks) {
            deleteServiceTaskById(serviceTask.getId());
        }

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

}