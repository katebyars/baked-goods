package dao;
import models.Cart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oCartDaoTest {

    private Sql2oCartDao cartDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cartDao = new Sql2oCartDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helper
    public Cart setUpCart () {
        return new Cart (0) ;

    }

    @Test
    public void addACart_True() throws Exception {
        Cart testCart = setUpCart();
        assertTrue(testCart instanceof Cart);
    }

    @Test
    public void addACartToDao_True() throws Exception {
        Cart testCart = setUpCart();
        cartDao.add(testCart);
        assertEquals(1,cartDao.getAllCarts().size());
    }

    @Test
    public void addACartSetsId() throws Exception {
        Cart testCart = setUpCart();
        cartDao.add(testCart);
        int idOfTest = testCart.getId();
        assertEquals(1, idOfTest);
    }
//
//    @Test
//    public void getAllLangugesGetsAllLanguages_True() {
//        Cart testCart = setUpCart();
//        Cart testCart2 = setUpCart();
//        Cart testCart3 = setUpCart();
//        cartDao.add(testCart);
//        cartDao.add(testCart2);
//        cartDao.add(testCart3);
//        assertEquals(3, cartDao.getAllCarts().size());
//    }
//
//    @Test
//    public void getLanguageByID() {
//        Cart testCart = setUpCart();
//        Cart testCart1 = setUpCart(13.00);
//        cartDao.add(testCart);
//        cartDao.add(testCart1);
//        assertEquals(0, cartDao.findById(1).getCartTotal());
//    }
//
//    @Test
//    public void updateChangesName() {
//        Cart testCart = setUpCart();
//        cartDao.add(testCart);
////        assertEquals("Turkish", languageDao.findById(1).getlanguagename());
//        cartDao.update(1, 0);
//        assertEquals(0, cartDao.findById(1).getCartTotal());
//    }
//
//    @Test
//    public void deleteALanguageFromTheDao_True() {
//        Cart testCart = setUpCart();
//        cartDao.add(testCart);
//        assertEquals(1, cartDao.getAllCarts().size());
//        cartDao.deleteById(1);
//        assertEquals(0, cartDao.getAllCarts().size());
//    }
//
//    @Test
//    public void deleteAllLanguages() {
//        Cart testCart = setUpCart();
//        cartDao.add(testCart);
//        assertEquals(1, cartDao.getAllCarts().size());
//        cartDao.deleteAll();
//        assertEquals(0, cartDao.getAllCarts().size());
//    }

}