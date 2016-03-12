package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DeleteAllVehiclesFromCustomer {

    public void deleteAllVehiclesFromCustomer(Person customer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        GetVehiclesByCustomerId getVehiclesByCustomerId = new GetVehiclesByCustomerId();
        List<Vehicle> vehicles = getVehiclesByCustomerId.getAllCarsByCustomerId(customer);

        deleteAllVehicles(entityManager, vehicles);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void deleteAllVehicles(EntityManager entityManager, List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            Vehicle vehicleToDelete = entityManager.find(Vehicle.class, vehicle.getId());
            entityManager.remove(vehicleToDelete);
        }
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}