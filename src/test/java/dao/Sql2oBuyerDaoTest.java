package dao;
import models.Buyer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oBuyerDaoTest {

    private Sql2oBuyerDao buyerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        buyerDao = new Sql2oBuyerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helper
    public Buyer setUpBuyer () {
        return new Buyer ("Betty Jean", "1234 Easy Street", "betty@bettyjeanbakedgoods.com", "Crumpets", 1);

    }

    @Test
    public void addBuyerAddsInstanceOfBuyer_True() throws Exception {
        Buyer testBuyer = setUpBuyer();
        assertTrue(testBuyer instanceof Buyer);
    }

    @Test
    public void addBuyerAddsABuyerToDao_True() throws Exception {
        Buyer testBuyer = setUpBuyer();
        buyerDao.add(testBuyer);
        assertEquals(1,buyerDao.getAll().size());
    }

    @Test
    public void addBuyerSetsId() throws Exception {
        Buyer testBuyer = setUpBuyer();
        buyerDao.add(testBuyer);
        int idOfTest = testBuyer.getId();
        assertEquals(1, idOfTest);
    }

    @Test
    public void getAllBuyersGetsAllBuyers_True() {
        Buyer testBuyer = setUpBuyer();
        Buyer testBuyer2 = setUpBuyer();
        Buyer testBuyer3 = setUpBuyer();
        buyerDao.add(testBuyer);
        buyerDao.add(testBuyer2);
        buyerDao.add(testBuyer3);
        assertEquals(3, buyerDao.getAll().size());
    }

    @Test
    public void getBuyerByID() {
        Buyer buyer = setUpBuyer();
        Buyer buyer1 = new Buyer("Jean Pierre", "1234 Easy Street", "none", "jeanniep@aol.com", 2);
        buyerDao.add(buyer);
        buyerDao.add(buyer1);
        assertEquals("Betty Jean", buyerDao.findById(1).getName());
    }

    @Test
    public void updateChangesName() {
        Buyer buyer = setUpBuyer();
        buyerDao.add(buyer);
        assertEquals("Betty Jean", buyerDao.findById(1).getName());
        buyerDao.update(1, "Jean Pierre", "1234 Easy Street", "none", "jeanniep@aol.com");
        assertEquals("Jean Pierre", buyerDao.findById(1).getName());
    }

    @Test
    public void deleteABuyerFromTheDao_True() {
        Buyer buyer = setUpBuyer();
        buyerDao.add(buyer);
        assertEquals(1, buyerDao.getAll().size());
        buyerDao.deleteById(1);
        assertEquals(0, buyerDao.getAll().size());
    }

    @Test
    public void deleteAllBuyers() {
        Buyer buyer = setUpBuyer();
        buyerDao.add(buyer);
        assertEquals(1, buyerDao.getAll().size());
        buyerDao.deleteAll();
        assertEquals(0, buyerDao.getAll().size());
    }

}