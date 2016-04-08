package nu.lansingcarworkshop.services.person;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;
import nu.lansingcarworkshop.services.servicetask.DeleteServiceTask;
import nu.lansingcarworkshop.services.servicetask.ReadServiceTask;
import nu.lansingcarworkshop.services.vehicle.DeleteVehicle;

import java.util.List;

public class DeletePerson {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public void deletePersonById(int personId) {
        entityManagerFacade.beginTransaction();

        Person person = entityManagerFacade.getEntityManager().find(Person.class, personId);

        removeSubsequentObjectsFromPerson(personId, person);

        entityManagerFacade.getEntityManager().remove(person);

        entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun();
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
            ServiceTask serviceTaskToRemoveEmployeeFrom = entityManagerFacade.getEntityManager().find(ServiceTask.class, serviceTask.getId());
            serviceTaskToRemoveEmployeeFrom.setResponsibleEmployee(null);
        }
    }

}