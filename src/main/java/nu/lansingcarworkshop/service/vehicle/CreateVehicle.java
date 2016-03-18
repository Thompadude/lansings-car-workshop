package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Customer;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;
import nu.lansingcarworkshop.service.coordinator.EntityManagerCoordinator;

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