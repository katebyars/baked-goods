package dao;
import java.util.List;

import models.Cart;
import models.Items;
import java.util.ArrayList;

public interface ItemsDao {

    //create
    void add(Items item);
    void addItemsToCart(Items item, Cart cart);

    //read
    List<Items> getAllItems();
    Items findById(int id);
    List<Items> findByCart(int id);

    //update
    void update(int id, String itemName, String itemCategory, double itemPrice);

    //delete
    void deleteById(int id);
    void deleteAll();
}
