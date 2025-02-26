package org.example;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private boolean discountEligible;

    public Item(String name, double price, int quantity, boolean discountEligible) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discountEligible = discountEligible;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isDiscountEligible() {
        return discountEligible;
    }

    public String getName() {
        return name;
    }
}

