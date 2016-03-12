package nu.lansingcarworkshop.service.vehicle;

import nu.lansingcarworkshop.entity.person.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class GetVehiclesByCustomerId {

    public List getAllCarsByCustomerId(Person customer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT car FROM Car car JOIN Customer cus ON car.customer.id = cus.id WHERE cus.id LIKE :id");
        query.setParameter("id", customer.getId());

        List cars = query.getResultList();
        return cars;
    }

}