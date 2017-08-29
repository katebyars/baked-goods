package models;
import models.Cart;

import java.util.List;

public class Seller extends Cart {

    private String name;
    private String address;
    private String email;
    private String goodsCategory;
    private List<Items> goodsInventory;
    private int id;

    public Seller(List cartItems, double cartTotal, String name, String address, String email, String goodsCategory) {
        super(cartItems, cartTotal);
        this.name = name;
        this.address = address;
        this.email = email;
        this.goodsCategory = goodsCategory;
        this.goodsInventory = goodsInventory;
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
        return result;
    }
}
