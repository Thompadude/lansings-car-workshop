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

        removeSubsequentObjectsFromPerson(personId, person);

        entityManagerCoordinator.getEntityManager().remove(person);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void removeSubsequentObjectsFromPerson(int personId, Person person) {
        if (person instanceof Customer) {
            removeSubsequentObjectsFromCustomer(personId);
        } else {
            removeEmployeeFromServiceTasks(personId);
        }
    }

    private void removeSubsequentObjectsFromCustomer(int personId) {
        removeCustomerVehicles(personId);
        removeCustomerServiceTasks(personId);
    }

    private void removeCustomerServiceTasks(int personId) {
        DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
        deleteServiceTask.deleteAllServiceTasksFromCustomer(personId);
    }

    private void removeCustomerVehicles(int personId) {
        DeleteVehicle deleteVehicle = new DeleteVehicle();
        deleteVehicle.deleteAllVehiclesFromCustomer(personId);
    }

    private void removeEmployeeFromServiceTasks(int personId) {
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