package nu.lansingcarworkshop.services.servicetask;

import nu.lansingcarworkshop.models.servicetask.ServiceTask;
import nu.lansingcarworkshop.services.facade.EntityManagerFacade;

import javax.persistence.Query;
import java.util.List;

public class ReadServiceTask {

    private EntityManagerFacade entityManagerFacade = new EntityManagerFacade();
    private Query queryResponse;

    public List getAllServiceTasks() {
        return entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s ORDER BY s.time").getResultList();
    }

    public List getTodaysServiceTasks() {
        return entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('DAY', s.time) = FUNCTION('DAY', CURRENT_DATE) AND FUNCTION('MONTH', s.time) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', s.time) = FUNCTION('YEAR', CURRENT_DATE)").getResultList();
    }

    public List getThisMonthsServiceTasks() {
        return entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('MONTH', s.time) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', s.time) = FUNCTION('YEAR', CURRENT_DATE)").getResultList();
    }

    public List getNextMonthsServiceTasks() {
        return entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE FUNCTION('MONTH', s.time) = (FUNCTION('MONTH', CURRENT_DATE) + 1) AND FUNCTION('YEAR', s.time) = FUNCTION('YEAR', CURRENT_DATE)").getResultList();
    }

    public ServiceTask getServiceTaskById(int serviceTaskId) {
        return entityManagerFacade.getEntityManager().find(ServiceTask.class, serviceTaskId);
    }

    public List getAllServiceTasksByVehicleId(int vehicleId) {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.vehicle.id = :id");
        queryResponse.setParameter("id", vehicleId);
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByEmployeeId(int employeeId) {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.responsibleEmployee.id = :id");
        queryResponse.setParameter("id", employeeId);
        return queryResponse.getResultList();
    }

    public List getAllServiceTasksByCustomerId(int customerId) {
        queryResponse = entityManagerFacade.getEntityManager().createQuery("SELECT s FROM ServiceTask s WHERE s.customer.id = :id");
        queryResponse.setParameter("id", customerId);
        return queryResponse.getResultList();
    }

}