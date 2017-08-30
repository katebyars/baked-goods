package models;
import models.Cart;

import java.util.List;

public class Seller extends User {

    private String goodsCategory;
    private List<Items> goodsInventory;
    private int id;
    private int cartId;

    public Seller(String name, String address, String dietaryPreference, String email, String goodsCategory) {
        super(name, address, dietaryPreference, email);
        this.goodsCategory = goodsCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public List<Items> getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(List<Items> goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Seller seller = (Seller) o;

        if (id != seller.id) return false;
        if (cartId != seller.cartId) return false;
        if (!name.equals(seller.name)) return false;
        if (!address.equals(seller.address)) return false;
        if (!email.equals(seller.email)) return false;
        if (!goodsCategory.equals(seller.goodsCategory)) return false;
        return goodsInventory != null ? goodsInventory.equals(seller.goodsInventory) : seller.goodsInventory == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + goodsCategory.hashCode();
        result = 31 * result + (goodsInventory != null ? goodsInventory.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + cartId;
        return result;
    }
}
