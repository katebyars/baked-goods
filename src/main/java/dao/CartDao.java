package dao;

import models.Cart;
import models.Items;

import java.util.List;

/**
 * Created by Guest on 8/29/17.
 */
public interface CartDao {

    //create
    void add(Cart cart);

    //read
    List<Cart> getAllCarts();
    Cart findById(int id);
//    Cart findByBuyer(int buyerId);

//    //update
    void updateCart(int id, double cartTotal);

//    //delete
    void deleteById(int id);
//    void deleteAll();
}
