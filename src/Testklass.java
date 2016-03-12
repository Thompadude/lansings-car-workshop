import nu.lansingcarworkshop.entity.person.ContactInformation;
import nu.lansingcarworkshop.entity.person.Customer;
import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.person.Sex;
import nu.lansingcarworkshop.entity.vehicle.Car;
import nu.lansingcarworkshop.service.person.GetPersonById;
import nu.lansingcarworkshop.service.vehicle.GetVehiclesByCustomerId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class Testklass {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static List queryResult;

    public static void main(String[] args) {



    }

    private static void query() {

        Query query = entityManager.createQuery("select car from Car car join Customer cus on car.customer.id = cus.id where cus.id like :id");
        query.setParameter("id", 2251);

        queryResult = query.getResultList();

        for (Object object : queryResult) {
            System.out.println("***********   " + object.getClass().getName());
            System.out.println("***********   " + object.toString());
        }
    }

    private static void testManyToOne() {
        entityManager.getTransaction().begin();

        Customer customer = new Customer("Bildrottningen", new ContactInformation("Drottninggatan", "321"), LocalDate.now(), Sex.FEMALE);
        entityManager.persist(customer);

        Car car1 = new Car("fgt234", "Porche", LocalDate.now(), "Bensin");
        car1.setCustomer(customer);

        Car car2 = new Car("ddd222", "Jeep", LocalDate.now(), "Diesel");
        car2.setCustomer(customer);

        Car car3 = new Car("fff333", "Hummer", LocalDate.now(), "Diesel");
        car3.setCustomer(customer);

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

//    public static void deleteAllVehiclesFromCustomer(Person customer) {
//        GetVehiclesByCustomerId getVehiclesByCustomerId = new GetVehiclesByCustomerId();
//        List<Car> cars = getVehiclesByCustomerId.getAllCarsByCustomerId(customer);
//        entityManager.getTransaction().begin();
//
//
//        for (Car car : cars) {
//            car.setCustomer(null);
//        }
//
//        commitAndCloseDatabase(entityManagerFactory, entityManager);
//    }

    private static void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void deletePerson() {

        entityManager.getTransaction().begin();

        Person person = entityManager.find(Person.class, 2702);

        entityManager.remove(person);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

}
