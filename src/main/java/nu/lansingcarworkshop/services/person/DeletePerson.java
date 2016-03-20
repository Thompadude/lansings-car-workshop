package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;
import nu.lansingcarworkshop.services.servicetask.DeleteServiceTask;
import nu.lansingcarworkshop.services.servicetask.ReadServiceTask;
import nu.lansingcarworkshop.services.vehicle.DeleteVehicle;

import java.util.List;

public class DeletePerson {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void deletePersonById(int personId) {
        entityManagerCoordinator.beginTransaction();

        Person person = entityManagerCoordinator.getEntityManager().find(Person.class, personId);

        removeSubObjects(personId, person);

        entityManagerCoordinator.getEntityManager().remove(person);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void removeSubObjects(int personId, Person person) {
        if (person instanceof Customer) {
            removeSubObjectsFromCustomer(personId);
        } else {
            removeEmployeeFromServiceTask(personId);
        }
    }

    private void removeSubObjectsFromCustomer(int personId) {
        DeleteVehicle deleteVehicle = new DeleteVehicle();
        deleteVehicle.deleteAllVehiclesFromCustomer(personId);

        DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
        deleteServiceTask.deleteAllServiceTasksFromCustomer(personId);
    }

    private void removeEmployeeFromServiceTask(int personId) {
        @SuppressWarnings("unchecked")
        List<ServiceTask> serviceTasks = getServiceTasks(personId);

        findEmployeeConnectedToServiceTaskAndRemove(serviceTasks);
    }

    private List getServiceTasks(int personId) {
        ReadServiceTask readServiceTask = new ReadServiceTask();
        return readServiceTask.getAllServiceTasksByEmployeeId(personId);
    }

    private void findEmployeeConnectedToServiceTaskAndRemove(List<ServiceTask> serviceTasks) {
        for (ServiceTask serviceTask : serviceTasks) {
            ServiceTask serviceTaskToRemoveEmployeeFrom = entityManagerCoordinator.getEntityManager().find(ServiceTask.class, serviceTask.getId());
            serviceTaskToRemoveEmployeeFrom.setResponsibleEmployee(null);
        }
    }

}