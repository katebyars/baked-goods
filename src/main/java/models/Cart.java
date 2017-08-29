package models;

import java.util.List;

public class Cart {

    private List<Items> cartItems;
    private double cartTotal;

    public Cart(List<Items> cartItems, double cartTotal) {
        this.cartItems = cartItems;
        this.cartTotal = 0;
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

    public void setCartTotal(double cartTotal) {
        this.cartTotal = cartTotal;
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
