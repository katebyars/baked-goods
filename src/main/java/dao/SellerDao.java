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
    void update(int id, double cartTotal, String name, String address, String email, String goodsCategory);

    //delete
    void deleteById(int id); //see above

    void deleteAll();
}
