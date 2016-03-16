package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Customer;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateVehicle {

    public void createVehicle(Vehicle vehicle, int customerId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(vehicle);

        setVehicleOwner(vehicle, customerId, entityManager);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void setVehicleOwner(Vehicle vehicle, int customerId, EntityManager entityManager) {
        Customer customerToAddVehicleTo = entityManager.find(Customer.class, customerId);
        vehicle.setCustomer(customerToAddVehicleTo);
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}