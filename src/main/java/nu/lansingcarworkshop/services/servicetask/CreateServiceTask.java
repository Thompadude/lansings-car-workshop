package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.person.Person;
import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.EntityManager;

public class CreateServiceTask {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void createServiceTask(ServiceTask serviceTask, Vehicle vehicle, Person person) {
        entityManagerCoordinator.beginTransactionAndSaveEntity(serviceTask);

        setVehicleInService(serviceTask, vehicle.getId(), entityManagerCoordinator.getEntityManager());

        setResponsibleEmployee(serviceTask, person.getId(), entityManagerCoordinator.getEntityManager());

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
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
