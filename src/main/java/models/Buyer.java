//package models;
//import models.Cart;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Buyer extends Cart {
//
//    private String name;
//    private String deliveryAddress;
//    private String dietaryPreference;
//    private String emailAddress;
//    private int id;
//
//
//    public Buyer(String name, String deliveryAddress, String dietaryPreference, String emailAddress, List cartItems, double cartTotal) {
//        super(cartItems, cartTotal);
//        this.name = name;
//        this.deliveryAddress = deliveryAddress;
//        this.dietaryPreference = dietaryPreference;
//        this.emailAddress = emailAddress;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDeliveryAddress() {
//        return deliveryAddress;
//    }
//
//    public void setDeliveryAddress(String deliveryAddress) {
//        this.deliveryAddress = deliveryAddress;
//    }
//
//    public String getDietaryPreference() {
//        return dietaryPreference;
//    }
//
//    public void setDietaryPreference(String dietaryPreference) {
//        this.dietaryPreference = dietaryPreference;
//    }
//
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Buyer buyer = (Buyer) o;
//
//        if (!name.equals(buyer.name)) return false;
//        if (!deliveryAddress.equals(buyer.deliveryAddress)) return false;
//        if (dietaryPreference != null ? !dietaryPreference.equals(buyer.dietaryPreference) : buyer.dietaryPreference != null)
//            return false;
//        return emailAddress.equals(buyer.emailAddress);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + name.hashCode();
//        result = 31 * result + deliveryAddress.hashCode();
//        result = 31 * result + (dietaryPreference != null ? dietaryPreference.hashCode() : 0);
//        result = 31 * result + emailAddress.hashCode();
//        return result;
//    }
//}
