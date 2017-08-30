package dao;
//import models.Seller;
import models.Buyer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oBuyerDao implements BuyerDao {

    private final Sql2o sql2o;

    public Sql2oBuyerDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Buyer buyer) {
        String sql = "INSERT INTO buyers (name, address, dietaryPreference, email, cartId) VALUES (:name, :address, :dietaryPreference, :email, :cartId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(buyer)
                    .executeUpdate()
                    .getKey();
            buyer.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Buyer> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM buyers")
                    .executeAndFetch(Buyer.class);
        }

    }

    @Override
    public Buyer findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM buyers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Buyer.class);
        }

    }

    @Override
    public void update(int id, String name, String address, String dietaryPreference, String email){
        String sql = "UPDATE buyers SET (name, address, dietaryPreference, email) = (:name, :address, :dietaryPreference, :email) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("dietaryPreference", dietaryPreference)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from buyers WHERE id=:id";
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
        String sql = "DELETE from buyers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
