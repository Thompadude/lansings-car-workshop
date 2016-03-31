package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

public class UpdateVehicle {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();
    private Vehicle vehicleWithUpdatedAttributes;

    /**
     * Fetch the vehicle to update from the database and use the temp vehicle's attributes for the update.
     * The temp vehicle have the same ID number as the vehicle in the database.
     *
     * @param vehicleWithUpdatedAttributes is the temp vehicle. Is used as the placeholder for the new attributes.
     */
    public void updateVehicle(Vehicle vehicleWithUpdatedAttributes) {
        this.vehicleWithUpdatedAttributes = vehicleWithUpdatedAttributes;

        entityManagerFacade.beginTransaction();

        Vehicle vehicleToUpdate = entityManagerFacade.getEntityManager().find(Vehicle.class, vehicleWithUpdatedAttributes.getId());
        setNewAttributes(vehicleToUpdate);

        entityManagerFacade.commitTransactionAndCloseDatabase();
    }

    private void setNewAttributes(Vehicle vehicleToUpdate) {
        vehicleToUpdate.setRegistrationPlate(vehicleWithUpdatedAttributes.getRegistrationPlate());
        vehicleToUpdate.setMake(vehicleWithUpdatedAttributes.getMake());
        vehicleToUpdate.setFuel(vehicleWithUpdatedAttributes.getFuel());
        vehicleToUpdate.setModelYear(vehicleWithUpdatedAttributes.getModelYear());
    }

}
