package nu.lansingcarworkshop.services.vehicle;

import nu.lansingcarworkshop.models.vehicle.Vehicle;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.Query;
import java.util.List;

public class ReadVehicle {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();

    public Vehicle getVehicleById(int vehicleId) {
        return entityManagerFacade.getEntityManager().find(Vehicle.class, vehicleId);
    }

    public List getAllVehicles() {
        return entityManagerFacade.getEntityManager().createQuery("SELECT v FROM Vehicle v").getResultList();
    }

    public List getAllVehiclesByCustomerId(int customerId) {
        Query query = entityManagerFacade.getEntityManager().createQuery("SELECT v FROM Vehicle v WHERE v.customer.id = :id");
        query.setParameter("id", customerId);
        return query.getResultList();
    }

}