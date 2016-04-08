package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.person.Customer;
import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.EntityManager;

public class CreateVehicle {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public void createVehicle(Vehicle vehicle, int customerId) {
        entityManagerFacade.beginTransactionAndSaveEntity(vehicle);

        setVehicleOwner(vehicle, customerId, entityManagerFacade.getEntityManager());

        entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun();
    }

    private void setVehicleOwner(Vehicle vehicle, int customerId, EntityManager entityManager) {
        Customer customerToAddVehicleTo = entityManager.find(Customer.class, customerId);
        vehicle.setCustomer(customerToAddVehicleTo);
    }

}