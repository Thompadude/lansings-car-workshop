package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UpdateVehicle {

    private Vehicle vehicleWithUpdatedAttributes;

    /**
     * Fetch the vehicle to update from the database and use the temp vehicle's attributes for the update.
     * The temp vehicle have the same ID number as the vehicle in the database.
     *
     * @param vehicleWithUpdatedAttributes is the temp vehicle. Is used as the placeholder for the new attributes.
     */
    public void updateVehicle(Vehicle vehicleWithUpdatedAttributes) {
        this.vehicleWithUpdatedAttributes = vehicleWithUpdatedAttributes;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle vehicleToUpdate = entityManager.find(Vehicle.class, vehicleWithUpdatedAttributes.getId());
        setNewAttributes(vehicleToUpdate);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void setNewAttributes(Vehicle vehicleToUpdate) {
        vehicleToUpdate.setRegistrationPlate(vehicleWithUpdatedAttributes.getRegistrationPlate());
        vehicleToUpdate.setMake(vehicleWithUpdatedAttributes.getMake());
        vehicleToUpdate.setFuel(vehicleWithUpdatedAttributes.getFuel());
        vehicleToUpdate.setModelYear(vehicleWithUpdatedAttributes.getModelYear());
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}
