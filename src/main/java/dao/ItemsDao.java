package dao;
import com.sun.tools.javac.util.List;
import models.Items;

public interface ItemsDao {

    //create
    void add();

    //read
    List<Items> getAll();

    Items findById(int id);

    //update
    void update(int id, double cartTotal, String name, String address, String email, String goodsCategory);

    //delete
    void deleteById(int id);
    void deleteAll();
}
