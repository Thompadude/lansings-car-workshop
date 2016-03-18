package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

public class UpdateVehicle {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();
    private Vehicle vehicleWithUpdatedAttributes;

    /**
     * Fetch the vehicle to update from the database and use the temp vehicle's attributes for the update.
     * The temp vehicle have the same ID number as the vehicle in the database.
     *
     * @param vehicleWithUpdatedAttributes is the temp vehicle. Is used as the placeholder for the new attributes.
     */
    public void updateVehicle(Vehicle vehicleWithUpdatedAttributes) {
        this.vehicleWithUpdatedAttributes = vehicleWithUpdatedAttributes;

        entityManagerCoordinator.beginTransaction();

        Vehicle vehicleToUpdate = entityManagerCoordinator.getEntityManager().find(Vehicle.class, vehicleWithUpdatedAttributes.getId());
        setNewAttributes(vehicleToUpdate);

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void setNewAttributes(Vehicle vehicleToUpdate) {
        vehicleToUpdate.setRegistrationPlate(vehicleWithUpdatedAttributes.getRegistrationPlate());
        vehicleToUpdate.setMake(vehicleWithUpdatedAttributes.getMake());
        vehicleToUpdate.setFuel(vehicleWithUpdatedAttributes.getFuel());
        vehicleToUpdate.setModelYear(vehicleWithUpdatedAttributes.getModelYear());
    }

}
