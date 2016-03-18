package nu.lansingcarworkshop.service.coordinator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCoordinator {

    private static final String DATABASE_URL = "carworkshop";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EntityManagerCoordinator() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_URL);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean beginTransaction() {
        boolean isTransactionStarted = false;
        if (!this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().begin();
            isTransactionStarted = true;
        }
        return isTransactionStarted;
    }

    public boolean commitTransactionAndCloseDatabase() {
        boolean isTransactionCompletedAndDatabaseClosed = false;
        if (this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().commit();
            this.entityManager.close();
            this.entityManagerFactory.close();
            isTransactionCompletedAndDatabaseClosed = true;
        }
        return isTransactionCompletedAndDatabaseClosed;
    }

    public <T> void beginTransactionAndSaveEntity(T t) {
        beginTransaction();
        this.entityManager.persist(t);
    }

    public <T> void beginTransactionSaveEntityAndCommit(T t) {
        beginTransactionAndSaveEntity(t);
        commitTransactionAndCloseDatabase();
    }

}