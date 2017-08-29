package models;

public class Items {

    private String itemName;
    private String itemCategory;
    private double itemPrice;

    public Items(String itemName, String itemCategory, double itemPrice) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
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

        if (!itemName.equals(items.itemName)) return false;
        return itemCategory.equals(items.itemCategory);
    }

    @Override
    public int hashCode() {
        int result = itemName.hashCode();
        result = 31 * result + itemCategory.hashCode();
        return result;
    }
}
