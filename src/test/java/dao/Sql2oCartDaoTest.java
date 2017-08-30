package dao;
import models.Cart;
import models.Items;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oCartDaoTest {

    private Sql2oCartDao cartDao;
    private Sql2oItemsDao itemsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cartDao = new Sql2oCartDao(sql2o);
        itemsDao = new Sql2oItemsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helpers
    public Cart setUpCart () {
        return new Cart (0.0) ;
    }

    public Items setUpItem () {
        return new Items ("Crumpet", "Tea Time", 5.00) ;
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

    @Test
    public void getAllCarts_True() {
        Cart testCart = setUpCart();
        Cart testCart2 = setUpCart();
        Cart testCart3 = setUpCart();
        cartDao.add(testCart);
        cartDao.add(testCart2);
        cartDao.add(testCart3);
        assertEquals(3, cartDao.getAllCarts().size());
    }

    @Test
    public void getACartById() {
        Cart testCart = setUpCart();
        Cart testCart1 = new Cart (13.12);
        cartDao.add(testCart);
        cartDao.add(testCart1);
        assertEquals(13.12, cartDao.findById(2).getCartTotal(), .01);
    }

    @Test
    public void updateCartChangesTotal() {
        Cart testCart = setUpCart();
        cartDao.add(testCart);
        cartDao.updateCart(1, 15.01);
        assertEquals(15.01, cartDao.findById(1).getCartTotal(), .01);
    }

    @Test
    public void deleteACartFromTheDao_True() {
        Cart testCart = setUpCart();
        cartDao.add(testCart);
        assertEquals(1, cartDao.getAllCarts().size());
        cartDao.deleteById(1);
        assertEquals(0, cartDao.getAllCarts().size());
    }

    @Test
    public void deleteAllCarts() {
        Cart testCart = setUpCart();
        cartDao.add(testCart);
        assertEquals(1, cartDao.getAllCarts().size());
        cartDao.deleteAll();
        assertEquals(0, cartDao.getAllCarts().size());
    }

    @Test
    public void addRowToCarts_Items_True() throws Exception {
        Cart testCart = setUpCart();
        Items testItem = setUpItem();
        cartDao.add(testCart);
        itemsDao.add(testItem);
        //add them to the carts_items table
    }
}