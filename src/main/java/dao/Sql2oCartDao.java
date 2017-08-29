package dao;
import models.Cart;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oCartDao implements CartDao {


    private final Sql2o sql2o;

    public Sql2oCartDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Cart cart) {
        String sql = "INSERT INTO carts (cartTotal) VALUES (:cartTotal)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(cart)
                    .executeUpdate()
                    .getKey();
            cart.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM carts")
                    .executeAndFetch(Cart.class);
        }

    }

    @Override
    public Cart findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM carts WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cart.class);
        }

    }

    @Override
    public void updateCart(int id, double cartTotal){
        String sql = "UPDATE carts SET cartTotal = :cartTotal WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("cartTotal", cartTotal)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
//
//    @Override
//    public void deleteById(int id) {
//        String sql = "DELETE from carts WHERE id=:id";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//
//    }
//
//    @Override
//    public void deleteAll() {
//        String sql = "DELETE from carts";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }

}
