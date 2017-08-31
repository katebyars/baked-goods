package models;

public class Items {

    private String itemName;
    private String itemCategory;
    private double itemPrice;
    private int id;
    private int cartId;
    private int sellerId;

    public Items(String itemName, String itemCategory, double itemPrice) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.id = id;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items items = (Items) o;

        if (Double.compare(items.itemPrice, itemPrice) != 0) return false;
        if (id != items.id) return false;
        if (cartId != items.cartId) return false;
        if (!itemName.equals(items.itemName)) return false;
        return itemCategory.equals(items.itemCategory);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemName.hashCode();
        result = 31 * result + itemCategory.hashCode();
        temp = Double.doubleToLongBits(itemPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + id;
        result = 31 * result + cartId;
        return result;
    }
}
