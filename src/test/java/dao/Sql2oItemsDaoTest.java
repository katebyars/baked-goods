package dao;
import models.Items;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oLanguageDaoTest {

    private Sql2oItemsDao itemsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        itemsDao = new Sql2oLanguageDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helper
    public Items setUpItem () {
        return new Items ("Crumpet", "Tea Time", 5.00) ;

    }

    @Test
    public void addLanguageAddsInstanceOfLanguage_True() throws Exception {
        Items testItem = setUpItem();
        assertTrue(testItem instanceof Items);
    }

    @Test
    public void addLanguageAddsALanguageToDao_True() throws Exception {
        Items testItem = setUpItem();
        itemsDao.add(testItem);
        assertEquals(1,itemsDao.getAll().size());
    }

    @Test
    public void addLanguageSetsId() throws Exception {
        Items testItem = setUpItem();
        itemsDao.add(testItem);
        int idOfTest = testItem.getId();
        assertEquals(1, idOfTest);
    }

    @Test
    public void getAllLangugesGetsAllLanguages_True() {
        Items testItem = setUpItem();
        Items testItem2 = setUpItem();
        Items testItem3 = setUpItem();
        itemsDao.add(testItem);
        itemsDao.add(testItem2);
        itemsDao.add(testItem3);
        assertEquals(3, itemsDao.getAll().size());
    }

    @Test
    public void getLanguageByID() {
        Items testItem = setUpItem();
        Items testItem2 = new Items("Milk Duds", "Cinema Treats", 5.00);
        itemsDao.add(testItem);
        itemsDao.add(testItem2);
        assertEquals("Milk Duds", itemsDao.findById(1).getName());
    }

    @Test
    public void updateChangesName() {
        Items testItem = setUpItem();
        itemsDao.add(testItem);
        itemsDao.update(1, 13.00, "Italian Wedding Cookies");
        assertEquals("Italian", itemsDao.findById(1).getlanguagename());
    }

    @Test
    public void deleteALanguageFromTheDao_True() {
        Items testItem = setUpItem();
        itemsDao.add(language);
        assertEquals(1, itemsDao.getAll().size());
        itemsDao.deleteById(1);
        assertEquals(0, itemsDao.getAll().size());
    }

    @Test
    public void deleteAllLanguages() {
        Items testItem = setUpItem();
        itemsDao.add(language);
        assertEquals(1, itemsDao.getAll().size());
        itemsDao.deleteAll();
        assertEquals(0, itemsDao.getAll().size());
    }

}