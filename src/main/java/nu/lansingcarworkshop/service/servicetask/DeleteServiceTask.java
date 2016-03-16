package nu.lansingcarworkshop.service.servicetask;

import nu.lansingcarworkshop.entity.person.Employee;
import nu.lansingcarworkshop.entity.person.Person;
import nu.lansingcarworkshop.entity.servicetask.ServiceTask;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DeleteServiceTask {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("carworkshop");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void deleteServiceTaskById(int serviceTaskId) {
        entityManager.getTransaction().begin();

        ServiceTask serviceTask = entityManager.find(ServiceTask.class, serviceTaskId);

        entityManager.remove(serviceTask);

        commitAndCloseDatabase(entityManagerFactory, entityManager);
    }

    public void removeEmployeeFromServiceTask(int personId) {
        entityManager.getTransaction().begin();

        //TODO doMagic(). Maybe change from int personId to Person person. Create method for reading a service task based on employe id in ReadServiceTask.
    }

    private void commitAndCloseDatabase(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}