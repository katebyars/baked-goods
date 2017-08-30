package models;
import models.Cart;
import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {

    private int id;
    private int cartId;


    public Buyer(String name, String address, String dietaryPreference, String email, int cartId) {
        super(name, address, dietaryPreference, email);
        this.cartId = cartId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

        Buyer buyer = (Buyer) o;

        if (id != buyer.id) return false;
        return cartId == buyer.cartId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cartId;
        return result;
    }
}
