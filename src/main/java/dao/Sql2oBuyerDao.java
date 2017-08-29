package dao;
//import models.Seller;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;
//
//public class Sql2oBuyerDao implements BuyerDao {
//
//    private final Sql2o sql2o;
//
//    public Sql2oBuyerDao(Sql2o sql2o){
//        this.sql2o = sql2o;
//    }

//    @Override
//    public void add(Language language) {
//        String sql = "INSERT INTO languages (languagename) VALUES (:languagename)";
//        try(Connection con = sql2o.open()){
//            int id = (int) con.createQuery(sql)
//                    .bind(language)
//                    .executeUpdate()
//                    .getKey();
//            language.setId(id);
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//
//    }
//
//    @Override
//    public List<Language> getAll() {
//        try(Connection con = sql2o.open()){
//            return con.createQuery("SELECT * FROM languages")
//                    .executeAndFetch(Language.class);
//        }
//
//    }
//
//    @Override
//    public Language findById(int id) {
//        try (Connection con = sql2o.open()){
//            return con.createQuery("SELECT * FROM languages WHERE id = :id")
//                    .addParameter("id", id)
//                    .executeAndFetchFirst(Language.class);
//        }
//
//    }
//
//    @Override
//    public void update(int id, String languagename){
//        String sql = "UPDATE languages SET languagename = :languagename WHERE id=:id";
//        try(Connection con = sql2o.open()){
//            con.createQuery(sql)
//                    .addParameter("languagename", languagename)
//                    .addParameter("id", id)
//                    .executeUpdate();
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    @Override
//    public void deleteById(int id) {
//        String sql = "DELETE from languages WHERE id=:id";
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
//        String sql = "DELETE from languages";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }

//}
