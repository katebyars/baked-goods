package models;
import models.Items;
import java.util.List;

public class Cart{

    private List<Items> cartItems;
    private double cartTotal;
    private int id;

    public Cart(double cartTotal) {
        this.cartItems = cartItems;
        this.cartTotal = cartTotal;
    }

    public List<Items> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Items> cartItems) {
        this.cartItems = cartItems;
    }

    public double getCartTotal() {
        return cartTotal;
    }

    public double addCartTotal(){
        double totalCost = 0.0;
        for(int i = 0; i < cartItems.size(); i++) {
            totalCost += cartItems.get(i).getItemPrice();
        }
        return totalCost;
    }

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (Double.compare(cart.cartTotal, cartTotal) != 0) return false;
        return cartItems != null ? cartItems.equals(cart.cartItems) : cart.cartItems == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cartItems != null ? cartItems.hashCode() : 0;
        temp = Double.doubleToLongBits(cartTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
