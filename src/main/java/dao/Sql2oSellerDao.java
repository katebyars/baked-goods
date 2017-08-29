package dao;
import models.Cart;
import models.Items;
import models.Seller;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oSellerDao implements SellerDao {

    private final Sql2o sql2o;

    public Sql2oSellerDao (Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Seller seller) {
        String sql = "INSERT INTO sellers (name, address, dietarypreference, email, goodscategory) VALUES (:name, :address, :dietaryPreference, :email, :goodsCategory)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(seller)
                    .executeUpdate()
                    .getKey();
            seller.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Seller> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sellers")
                    .executeAndFetch(Seller.class);
        }

    }

    @Override
    public Seller findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sellers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Seller.class);
        }
    }

    @Override
    public void update(int id, String name, String address, String dietaryPreference, String email, String goodsCategory){
        String sql = "UPDATE sellers SET (name, address, dietarypreference, email, goodscategory) = (:name, :address, :dietaryPreference, :email, :goodsCategory) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("dietarypreference", dietaryPreference)
                    .addParameter("email", email)
                    .addParameter("goodsCategory", goodsCategory)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from sellers WHERE id=:id";
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
        String sql = "DELETE from sellers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addItemsToSellers(Seller seller, Items items){
        String sql = "INSERT INTO sellers_items (sellerId, itemId) VALUES (:sellerId, :itemId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("sellerId", seller.getId())
                    .addParameter("itemId", items.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Items> getAllItemsForASeller(int sellerId) {

        ArrayList<Items> items = new ArrayList<>();

        String joinQuery = "SELECT itemId FROM sellers_items WHERE sellerId = :sellerId";

        try (Connection con = sql2o.open()) {
            List<Integer> allItemIds = con.createQuery(joinQuery)
                    .addParameter("sellerId", sellerId)
                    .executeAndFetch(Integer.class);
            for (Integer allItemId : allItemIds){
                String restaurantQuery = "SELECT * FROM items WHERE id = :allItemId";
                items.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("itemId", allItemId)
                                .executeAndFetchFirst(Items.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return items;
    }
}
