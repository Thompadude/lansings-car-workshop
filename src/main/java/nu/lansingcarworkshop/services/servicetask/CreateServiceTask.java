package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.EntityManager;

public class CreateServiceTask {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public void createServiceTask(ServiceTask serviceTask, Vehicle vehicle, Person person) {
        entityManagerFacade.beginTransactionAndSaveEntity(serviceTask);

        setVehicleInService(serviceTask, vehicle.getId(), entityManagerFacade.getEntityManager());

        setResponsibleEmployee(serviceTask, person.getId(), entityManagerFacade.getEntityManager());

        entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun();
    }

    private void setResponsibleEmployee(ServiceTask serviceTask, int personId, EntityManager entityManager) {
        Employee responsibleEmployee = entityManager.find(Employee.class, personId);
        serviceTask.setResponsibleEmployee(responsibleEmployee);
    }

    private void setVehicleInService(ServiceTask serviceTask, int vehicleId, EntityManager entityManager) {
        Vehicle vehicleInService = entityManager.find(Vehicle.class, vehicleId);
        serviceTask.setVehicle(vehicleInService);
    }

}