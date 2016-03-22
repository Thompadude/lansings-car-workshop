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

    public List getAllVehicles() {
        return entityManagerCoordinator.getEntityManager().createQuery("SELECT v FROM Vehicle v").getResultList();
    }

    public List getAllVehiclesByCustomerId(int customerId) {
        Query query = entityManagerCoordinator.getEntityManager().createQuery("SELECT v FROM Vehicle v WHERE v.customer.id = :id");
        query.setParameter("id", customerId);
        return query.getResultList();
    }

}