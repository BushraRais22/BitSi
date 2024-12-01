package org.example.scd_db_project.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();



    public void addItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getMenuId() == item.getMenuId()) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    public void clear() {
        items.clear();
    }
}
