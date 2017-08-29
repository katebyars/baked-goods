package dao;
import models.Seller;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oItemsDao implements ItemsDao {

    private final Sql2o sql2o;

    public Sql2oItemsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

}
