package dao;
import models.Buyer;
import java.util.List;

public interface BuyerDao {

    //create
    void add(Buyer buyer);

    //read
    List<Buyer> getAll();

    Buyer findById(int id);

//    //update
    void update(int id, String name, String address, String dietaryPreference, String email);
//
//    //delete
    void deleteById(int id); //see above
//
    void deleteAll();
}

