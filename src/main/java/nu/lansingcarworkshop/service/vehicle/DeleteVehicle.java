package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DeleteVehicle {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void deleteVehicleById(int vehicleId) {
        entityManager.getTransaction().begin();

        Vehicle vehicle = entityManager.find(Vehicle.class, vehicleId);
        entityManager.remove(vehicle);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    public void deleteAllVehiclesFromCustomer(Person customer) {
        entityManager.getTransaction().begin();

        ReadVehicle readVehicle = new ReadVehicle();
        @SuppressWarnings("unchecked")
        List<Vehicle> vehicles = readVehicle.getAllCarsByCustomerId(customer);
        //TODO add more delete queries for other types of vehicles.

        deleteListOfVehicles(entityManager, vehicles);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    private void deleteListOfVehicles(EntityManager entityManager, List<Vehicle> vehicles) {
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