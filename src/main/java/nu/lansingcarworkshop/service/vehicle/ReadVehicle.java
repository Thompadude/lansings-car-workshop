package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ReadVehicle {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Vehicle getVehicleById(int vehicleId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Vehicle.class, vehicleId);
    }

    public List getAllCars() {
        Query queryVehicles = entityManager.createQuery("SELECT c FROM Car c ORDER BY c.customer.id");
        return queryVehicles.getResultList();
    }

    public List getAllCarsByCustomerId(Person customer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT car FROM Car car JOIN Customer cus ON car.customer.id = cus.id WHERE cus.id LIKE :id");
        query.setParameter("id", customer.getId());

        return query.getResultList();
    }

}