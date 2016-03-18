package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

import javax.persistence.EntityManager;
import java.util.List;

public class DeleteVehicle {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void deleteVehicleById(int vehicleId) {
        entityManagerCoordinator.beginTransaction();

        Vehicle vehicle = entityManagerCoordinator.getEntityManager().find(Vehicle.class, vehicleId);
        entityManagerCoordinator.getEntityManager().remove(vehicle);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    public void deleteAllVehiclesFromCustomer(int customerId) {
        entityManagerCoordinator.beginTransaction();

        ReadVehicle readVehicle = new ReadVehicle();
        @SuppressWarnings("unchecked")
        //TODO add more delete queries for other types of vehicles.
                List<Vehicle> vehicles = readVehicle.getAllCarsByCustomerId(customerId);

        deleteListOfVehicles(entityManagerCoordinator.getEntityManager(), vehicles);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void deleteListOfVehicles(EntityManager entityManager, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            Vehicle vehicleToDelete = entityManager.find(Vehicle.class, vehicle.getId());
            entityManager.remove(vehicleToDelete);
        }
    }

}