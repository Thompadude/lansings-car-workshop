package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.EntityManager;

public class CreateVehicle {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public void createVehicle(Vehicle vehicle, int customerId) {
        entityManagerCoordinator.beginTransactionAndSaveEntity(vehicle);

        setVehicleOwner(vehicle, customerId, entityManagerCoordinator.getEntityManager());

        entityManagerCoordinator.commitTransactionAndCloseDatabase();
    }

    private void setVehicleOwner(Vehicle vehicle, int customerId, EntityManager entityManager) {
        Customer customerToAddVehicleTo = entityManager.find(Customer.class, customerId);
        vehicle.setCustomer(customerToAddVehicleTo);
    }

}