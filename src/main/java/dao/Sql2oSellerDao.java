package dao;
import models.Cart;
import models.Seller;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oSellerDao implements SellerDao {

    private final Sql2o sql2o;

    public Sql2oSellerDao (Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Seller seller) {
        String sql = "INSERT INTO sellers (cartTotal, name, address, email, goodsCategory) VALUES (:cartTotal, :name, :address, :email, :goodsCategory)";
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
    public void update(int id, double cartTotal, String name, String address, String email, String goodsCategory){
        String sql = "UPDATE sellers SET cartTotal = :cartTotal, name = :name, address = :address, email = :email, goodsCategory = :goodsCategory WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("cartTotal", cartTotal)
                    .addParameter("name", name)
                    .addParameter("address", address)
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
}
