package org.example;

import java.util.List;

public class OrderProcessor {
    public static int[] nodes = new int[5];
    /**
     * Computes the total cost of all valid items.
     * - Items with negative price or non-positive quantity are skipped.
     * - A 10% discount is applied if total > 100.
     * - Otherwise, if any item is discount-eligible, apply a 5% discount.
     * - If after discounts total < 50, add a flat 5 shipping.
     *
     * @param items List of items to process.
     * @return The final total after discounts and shipping.
     */
    public double computeTotal(List<Item> items) {

        if (items.isEmpty()) {
            throw new RuntimeException("Item list is empty");
        }

        double total = getTotal(items);

        // Discount logic
        if (total > 100) {
            total *= 0.9; // 10% discount
        } else {
            // if any item is discountEligible, apply a 5% discount
            boolean discountFound = false;
            for (Item item : items) {
                if (item.isDiscountEligible()) {
                    discountFound = true;
                    break;
                }
            }
            if (discountFound) {
                total *= 0.95; // 5% discount
            }
        }

        // Shipping logic
        if (total < 50) {
            total += 5;
        }

        return total;
    }

    private static double getTotal(List<Item> items) {
        double total = 0.0;

        for (Item item : items) {
            nodes[0] += 1;
            if (item.getPrice() < 0 || item.getQuantity() <= 0) {

                nodes[3] += 1;

                continue;
            }
            nodes[1] += 1;

            double subTotal = item.getPrice() * item.getQuantity();
            total += subTotal;

            nodes[2] += 1;
        }
        nodes[4] += 1;
        return total;
    }
}
