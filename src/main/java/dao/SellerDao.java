package dao;
import models.Seller;

import java.util.List;

public interface SellerDao {

    //create
    void add(Seller seller);

    //read
    List<Seller> getAll();
    Seller findById(int id);

    //update
    void update(int id, double cartTotal, String name, String address, String email, String goodsCategory);

    //delete
    void deleteById(int id); //see above

    void deleteAll();
}
