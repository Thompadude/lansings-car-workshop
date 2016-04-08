package test.nu.lansingcarworkshop.services.facade;

import nu.lansingcarworkshop.services.facade.EntityManagerFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityManagerFacadeTest {

    private EntityManagerFacade entityManagerFacade;
//    private static final String DATABASE_URL = "carworkshop";
//    @Mock
//    private EntityManagerFactory entityManagerFactory;
//    @Mock
//    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFacade = new EntityManagerFacade();
//        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        entityManagerFacade = null;
    }

    @Test
    public void testBeginTransaction_Should_Only_Begin_Transaction_If_Not_Already_Started() throws Exception {
        assertTrue(entityManagerFacade.beginTransaction());

        // Start a transaction and then try start another one. Should fail and return false.
        entityManagerFacade.beginTransaction();
        assertFalse(entityManagerFacade.beginTransaction());
    }

    @Test
    public void testcommitTransactionAndCloseDatabaseIfATransactionHasBegun_Should_Only_Close_If_Transaction_Has_Begun() throws Exception {
        // Should return false since we haven't opened a transaction yet.
        assertFalse(entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun());

        // Begin a transaction and then commit and close the database. Should return true since the operation is executed.
        entityManagerFacade.beginTransaction();
        assertTrue(entityManagerFacade.commitTransactionAndCloseDatabaseIfATransactionHasBegun());
    }

    @Test
    public void testBeginTransactionAndSaveEntity() throws Exception {

    }

    @Test
    public void testBeginTransactionSaveEntityAndCommit() throws Exception {

    }
}