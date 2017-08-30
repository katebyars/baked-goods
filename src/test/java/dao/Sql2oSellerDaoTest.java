package dao;
import models.Items;
import models.Seller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oSellerDaoTest {

    private Sql2oSellerDao sellerDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sellerDao = new Sql2oSellerDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addSeller_True() throws Exception {
        Seller testSeller = setUpSeller();
        assertTrue(testSeller instanceof Seller);
    }

    @Test
    public void addSellerAddsASellerToDao_True() throws Exception {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        assertEquals(1,sellerDao.getAll().size());
    }

    @Test
    public void addSellerSetsId() throws Exception {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        int idOfTest = testSeller.getId();
        assertEquals(1, idOfTest);
    }

    @Test
    public void getAllSellersGetsAllSellers_True() {
        Seller testSeller = setUpSeller();
        Seller testSeller2 = setUpSeller();
        Seller testSeller3 = setUpSeller();
        sellerDao.add(testSeller);
        sellerDao.add(testSeller2);
        sellerDao.add(testSeller3);
        assertEquals(3, sellerDao.getAll().size());
    }

    @Test
    public void getSellerByID() {
        Seller testSeller = setUpSeller();
        Seller testSeller2 = new Seller ("Jean Pierre", "1234 Easy Street", "none", "jeanniep@aol.com", "Crumpets");
        sellerDao.add(testSeller);
        sellerDao.add(testSeller2);
        assertEquals("Jean Pierre", sellerDao.findById(1).getName());
    }

    @Test
    public void updateChangesName() {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
//        assertEquals("Turkish", languageDao.findById(1).getlanguagename());
        sellerDao.update(1, "Betty Ann", "1234 Easy Street",  "everything", "betty@bettyjeanbakedgoods.com", "Crumpets");
        assertEquals("Betty Ann", sellerDao.findById(1).getName());
    }

    @Test
    public void deleteASellerFromTheDao_True() {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        assertEquals(1, sellerDao.getAll().size());
        sellerDao.deleteById(1);
        assertEquals(0, sellerDao.getAll().size());
    }

    @Test
    public void deleteAllSellers() {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        assertEquals(1, sellerDao.getAll().size());
        sellerDao.deleteAll();
        assertEquals(0, sellerDao.getAll().size());
    }

    //helper
    public Seller setUpSeller () {
        return new Seller ("Betty Jean", "1234 Easy Street", "vegan/paleo/gluten-free(I make them all","betty@bettyjeanbakedgoods.com", "Crumpets");
    }
}