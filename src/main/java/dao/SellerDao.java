package dao;
import models.Items;
import models.Seller;

import java.util.List;

public interface SellerDao {

    //create
    void add(Seller seller);
    void addItemsToSellers(Seller seller, Items items);

    //read
    List<Seller> getAll();
    Seller findById(int id);
    List<Items> getAllItemsForASeller(int sellerId);

    //update
    void update(int id, String name, String address, String dietaryPreference, String email, String goodsCategory);

    //delete
    void deleteById(int id); //see above

    void deleteAll();
}
