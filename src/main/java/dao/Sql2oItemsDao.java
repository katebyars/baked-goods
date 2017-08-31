package dao;
import models.Cart;
import models.Items;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.ArrayList;
import java.util.List;

public class Sql2oItemsDao implements ItemsDao {

    private final Sql2o sql2o;

    public Sql2oItemsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Items item) {
        String sql = "INSERT INTO items (itemName, itemCategory, itemPrice) VALUES (:itemName, :itemCategory, :itemPrice)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(item)
                    .executeUpdate()
                    .getKey();
            item.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Items> getAllItems(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM items")
                    .executeAndFetch(Items.class);
        }
    }

    @Override
    public Items findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM items WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Items.class);
        }
    }

    @Override
    public void update(int id, String itemName, String itemCategory, double itemPrice){
        String sql = "UPDATE items SET itemName = :itemName, itemCategory =:itemCategory, itemPrice = :itemPrice WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("itemName", itemName)
                    .addParameter("itemCategory", itemCategory)
                    .addParameter("itemPrice", itemPrice)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from items WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void deleteAll() {
        String sql = "DELETE from items";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addItemsToCart(Items item, Cart cart){
        String query = "INSERT INTO carts_items (itemsId, cartId) VALUES (:itemsId, :cartId)";
        //add push operation to cartItems
        try(Connection con = sql2o.open()){
            con.createQuery(query)
                    .addParameter("itemsId", item.getId())
                    .addParameter("cartId", cart.getId())
                    .executeUpdate();
        } catch (Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public List<Items> findByCart(int cartId){
        List<Items> itemsInCart = new ArrayList<>();
        String query = "SELECT itemsId FROM carts_items WHERE cartId = :cartId";
        try(Connection con =sql2o.open()){
            List<Integer> allItemsIds = con.createQuery(query)
                    .addParameter("cartId", cartId)
                    .executeAndFetch(Integer.class);
            for (Integer allItemsId : allItemsIds) {
                String query2 = "SELECT * from items WHERE id = :allItemsId";
                itemsInCart.add(
                        con.createQuery(query2)
                                .addParameter("allItemsId", allItemsId)
                                .executeAndFetchFirst(Items.class));
            }
        }catch(Sql2oException e) {
            System.out.println(e);
        }
        return itemsInCart;
    }

}

