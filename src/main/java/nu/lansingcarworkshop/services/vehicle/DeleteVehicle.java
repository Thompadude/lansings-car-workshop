package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;
import nu.lansingcarworkshop.services.servicetask.DeleteServiceTask;

import javax.persistence.EntityManager;
import java.util.List;

public class DeleteVehicle {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public void deleteVehicleById(int vehicleId) {
        entityManagerFacade.beginTransaction();

        Vehicle vehicle = entityManagerFacade.getEntityManager().find(Vehicle.class, vehicleId);

        removeSubsequentObjectsFromVehicle(vehicleId);

        entityManagerFacade.getEntityManager().remove(vehicle);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    public void deleteAllVehiclesFromCustomer(int customerId) {
        entityManagerFacade.beginTransaction();

        ReadVehicle readVehicle = new ReadVehicle();

        @SuppressWarnings("unchecked")
        List<Vehicle> vehicles = readVehicle.getAllVehiclesByCustomerId(customerId);

        deleteListOfVehicles(entityManagerFacade.getEntityManager(), vehicles);

        entityManagerFacade.commitTransactionAndCloseDatabase();
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