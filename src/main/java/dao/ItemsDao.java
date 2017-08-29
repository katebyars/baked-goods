package dao;
import com.sun.tools.javac.util.List;
import models.Items;

public interface ItemsDao {

    //create
    void add(Items item);

    //read
    List<Items>getAllItems();
    Items findById(int id);

    //update
    void update(int id, String itemName, String itemCategory, double itemPrice);

    //delete
    void deleteById(int id);
    void deleteAll();
}
