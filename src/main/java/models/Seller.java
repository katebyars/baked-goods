package models;
import models.Cart;

import java.util.List;

public class Seller extends User {

    private String goodsCategory;
    private List<Items> goodsInventory;
    private int id;
    private int cartId;
    private String image;

    public Seller(String name, String address, String dietaryPreference, String email, String goodsCategory) {
        super(name, address, dietaryPreference, email);
        this.goodsCategory = goodsCategory;
        this.id = id;
        this.image = "/images/placeholder.png";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seller seller = (Seller) o;

        if (id != seller.id) return false;
        if (cartId != seller.cartId) return false;
        if (!goodsCategory.equals(seller.goodsCategory)) return false;
        return goodsInventory.equals(seller.goodsInventory);
    }

    @Override
    public int hashCode() {
        int result = goodsCategory.hashCode();
        result = 31 * result + goodsInventory.hashCode();
        result = 31 * result + id;
        result = 31 * result + cartId;
        return result;
    }
}
