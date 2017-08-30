package dao;
import models.Cart;
import models.Items;
import java.util.List;

public interface CartDao {

    //create
    void add(Cart cart);
    void addItemsToCart(Items item, Cart cart);

    //read
    List<Cart> getAllCarts();
    Cart findById(int id);
//    Cart findByBuyer(int buyerId);

//    //update
    void updateCart(int id, double cartTotal);

//    //delete
    void deleteById(int id);
    void deleteAll();
}
