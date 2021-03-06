package dao;
import models.Cart;
import models.Items;
import models.Seller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oSellerDaoTest {

    private Sql2oSellerDao sellerDao;
    private Sql2oItemsDao itemsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sellerDao = new Sql2oSellerDao(sql2o);
        itemsDao = new Sql2oItemsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }


    //helper
    public Seller setUpSeller() {
        return new Seller("Betty Jean", "1234 Easy Street", "betty@bettyjeanbakedgoods.com", "Crumpets", "");
    }

    //helper
    public Items setUpItems() {
        return new Items("Crumpet", "Tea Time", 5.00);

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
        assertEquals(1, sellerDao.getAll().size());
    }

    @Test
    public void addItemsToSellersAdds_True() throws Exception {

        Seller seller = setUpSeller();

        sellerDao.add(seller);


        Items testItems = setUpItems();
        Items otherItems = setUpItems();

        itemsDao.add(testItems);
        itemsDao.add(otherItems);

        sellerDao.addItemsToSeller(seller, testItems);
        sellerDao.addItemsToSeller(seller, otherItems);

        assertEquals(2, sellerDao.getAllItemsForASeller(testItems.getId()).size());
    }

    @Test
    public void addSellerSetsId() throws Exception {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        int idOfTest = testSeller.getId();
        assertEquals(1, idOfTest);
    }

    @Test
    public void getAllItemsForASellerReturnsItemsCorrectly() throws Exception {
        Items testItems = setUpItems();
        itemsDao.add(testItems);

        Items otherItems  = new Items("Milk Duds", "Cinema Treats", 5.00);
        itemsDao.add(otherItems);

        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        sellerDao.addItemsToSeller(testSeller,testItems);
        sellerDao.addItemsToSeller(testSeller,otherItems);
        System.out.println(sellerDao.getAllItemsForASeller(testSeller.getId()));

        Items[] items = {testItems, otherItems}; //oh hi what is this?

        assertEquals(sellerDao.getAllItemsForASeller(testSeller.getId()), Arrays.asList(items));
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
        Seller testSeller2 = new Seller("Jean Pierre", "1234 Easy Street", "none", "jeanniep@aol.com", "Crumpets");
        sellerDao.add(testSeller);
        sellerDao.add(testSeller2);
        assertEquals("Jean Pierre", sellerDao.findById(2).getName());
    }

    @Test
    public void updateChangesName() {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
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
    public void deletingSellerAlsoUpdatesJoinTable() throws Exception {
        Items testItems  = setUpItems();
        itemsDao.add(testItems);

        Items otherItems  = new Items("Milk Duds", "Cinema Treats", 5.00);
        itemsDao.add(otherItems);

        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);

        sellerDao.addItemsToSeller(testSeller, testItems);
        sellerDao.addItemsToSeller(testSeller, otherItems);

        sellerDao.deleteById(testSeller.getId());
        assertEquals(0, sellerDao.getAllItemsForASeller(testSeller.getId()).size());
    }

    @Test
    public void deleteAllSellers() {
        Seller testSeller = setUpSeller();
        sellerDao.add(testSeller);
        assertEquals(1, sellerDao.getAll().size());
        sellerDao.deleteAll();
        assertEquals(0, sellerDao.getAll().size());
    }

    @Test
    public void addItemsToSellers() throws Exception {
        Seller seller = setUpSeller();
        sellerDao.add(seller);

        Items item = setUpItems();
        itemsDao.add(item);

        sellerDao.addItemsToSeller(seller, item);

        assertEquals(1, sellerDao.findItemsBySeller(seller.getId()).size());
    }

    @Test
    public void findAllItemsBySeller_True() {
        Seller seller = setUpSeller();
        sellerDao.add(seller);

        Items item1 = setUpItems();
        itemsDao.add(item1);

        Items item2 = setUpItems();
        itemsDao.add(item2);

        sellerDao.addItemsToSeller(seller, item1);
        sellerDao.addItemsToSeller(seller, item2);

        Items[] items = {item1, item2};

        assertEquals(sellerDao.findItemsBySeller(seller.getId()).size(), Arrays.asList(items).size());
    }

}