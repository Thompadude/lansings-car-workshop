package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;
import nu.lansingcarworkshop.services.servicetask.DeleteServiceTask;

import javax.persistence.EntityManager;
import java.util.List;

public class DeleteVehicle {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void deleteVehicleById(int vehicleId) {
        entityManagerCoordinator.beginTransaction();

        Vehicle vehicle = entityManagerCoordinator.getEntityManager().find(Vehicle.class, vehicleId);

        removeSubsequentObjectsFromVehicle(vehicleId);

        entityManagerCoordinator.getEntityManager().remove(vehicle);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void deleteAllVehiclesFromCustomer(int customerId) {
        entityManagerCoordinator.beginTransaction();

        ReadVehicle readVehicle = new ReadVehicle();

        @SuppressWarnings("unchecked")
        List<Vehicle> vehicles = readVehicle.getAllVehiclesByCustomerId(customerId);

        deleteListOfVehicles(entityManagerCoordinator.getEntityManager(), vehicles);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void removeSubsequentObjectsFromVehicle(int vehicleId) {
        DeleteServiceTask deleteServiceTask = new DeleteServiceTask();
        deleteServiceTask.deleteAllServiceTasksFromVehicle(vehicleId);
    }

    private void deleteListOfVehicles(EntityManager entityManager, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            Vehicle vehicleToDelete = entityManager.find(Vehicle.class, vehicle.getId());
            entityManager.remove(vehicleToDelete);
        }
    }

}