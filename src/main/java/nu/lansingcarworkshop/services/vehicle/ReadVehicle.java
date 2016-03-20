package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.coordinator.EntityManagerCoordinator;

import javax.persistence.Query;
import java.util.List;

public class ReadVehicle {

    private EntityManagerCoordinator entityManagerCoordinator = new EntityManagerCoordinator();

    public Vehicle getVehicleById(int vehicleId) {
        return entityManagerCoordinator.getEntityManager().find(Vehicle.class, vehicleId);
    }

    public List getAllCars() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT c FROM Car c ORDER BY c.customer.id").getResultList();
    }

    public List getAllCarsByCustomerId(int customerId) {
        Query query = entityManagerCoordinator.getEntityManager().createQuery("SELECT car FROM Car car JOIN Customer cus ON car.customer.id = cus.id WHERE cus.id LIKE :id");
        query.setParameter("id", customerId);
        return query.getResultList();
    }

}