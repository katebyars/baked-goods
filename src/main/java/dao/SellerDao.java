package dao;
import models.Items;
import models.Seller;

import java.util.List;

public interface SellerDao {

    //create
    void add(Seller seller);
    void addItemsToSeller(Seller seller, Items items);

    //read
    List<Seller> getAll();
    List<Items> getAllItemsForASeller(int sellerId);

    Seller findById(int id);
    List<Items> findItemsBySeller(int sellerId);

    //update
    void update(int id, String name, String address, String dietaryPreference, String email, String goodsCategory);

    //delete
    void deleteById(int id); //see above

    void deleteAll();
}
