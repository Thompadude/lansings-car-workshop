package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import java.util.List;

public class DeleteServiceTask {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();
    private ReadServiceTask readServiceTask = new ReadServiceTask();
    private List serviceTasks;

    public void deleteServiceTaskById(int serviceTaskId) {
        entityManagerFacade.beginTransaction();

        ServiceTask serviceTask = entityManagerFacade.getEntityManager().find(ServiceTask.class, serviceTaskId);

        entityManagerFacade.getEntityManager().remove(serviceTask);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    public void deleteAllServiceTasksFromCustomer(int customerId) {
        entityManagerFacade.beginTransaction();

        serviceTasks = readServiceTask.getAllServiceTasksByCustomerId(customerId);

        //noinspection unchecked
        removeListOfServiceTasks(serviceTasks);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    public void deleteAllServiceTasksFromVehicle(int vehicleId) {
        entityManagerFacade.beginTransaction();

        serviceTasks = readServiceTask.getAllServiceTasksByVehicleId(vehicleId);

        //noinspection unchecked
        removeListOfServiceTasks(serviceTasks);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    private void removeListOfServiceTasks(List<ServiceTask> serviceTasks) {
        for (ServiceTask serviceTask : serviceTasks) {
            ServiceTask serviceTaskToDelete = entityManagerFacade.getEntityManager().find(ServiceTask.class, serviceTask.getId());
            entityManagerFacade.getEntityManager().remove(serviceTaskToDelete);
        }
    }

}