package test.nu.lansingcarworkshop.services.facade;

import nu.lansingcarworkshop.services.facade.EntityManagerFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityManagerFacadeTest {

    private EntityManagerFacade entityManagerFacade;

    @Before
    public void setUp() throws Exception {
        entityManagerFacade = new EntityManagerFacade();
    }

    @After
    public void tearDown() throws Exception {
        entityManagerFacade = null;
    }

    @Test
    public void testBeginTransaction_Should_Only_Begin_Transaction_If_Not_Already_Started() throws Exception {
        assertTrue(entityManagerFacade.beginTransaction());

        // Start a transaction and then try start another one. Should not another transaction thus returning false.
        entityManagerFacade.beginTransaction();
        assertFalse(entityManagerFacade.beginTransaction());
    }

    @Test
    public void testCommitTransactionAndCloseDatabaseIfATransactionHasBegun_Should_Only_Close_If_Transaction_Has_Begun() throws Exception {
        // Should return false since we haven't opened a transaction yet.
        assertFalse(entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun());

        // Begin a transaction and then commit and close the database. Should return true since the operation is executed.
        entityManagerFacade.beginTransaction();
        assertTrue(entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun());
    }

}