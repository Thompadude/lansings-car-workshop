import nu.lansingcarworkshop.entity.person.Employee;
import nu.lansingcarworkshop.entity.servicetask.ServiceTask;
import nu.lansingcarworkshop.service.person.ReadPerson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class Testklass {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();
    static List queryResult;

    public static void main(String[] args) {
        testService();
    }

    private static void testService() {
        entityManager.getTransaction().begin();

        LocalDateTime localDateTime = LocalDateTime.now();
        ServiceTask serviceTask = new ServiceTask(localDateTime, "Byt däck", (Employee) new ReadPerson().getPersonById(2804));

        entityManager.persist(serviceTask);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
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

    private static void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private void testQueries() {
        Query query = entityManager.createQuery("SELECT COUNT(c.name) FROM Customer c");

    }

}