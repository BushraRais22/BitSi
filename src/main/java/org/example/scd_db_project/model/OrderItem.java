package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitem_seq_generator")
    @SequenceGenerator(name = "orderitem_seq_generator", sequenceName = "ORDERITEM_SEQ", allocationSize = 1)
    private int orderitem_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ro_id" , foreignKey = @ForeignKey(name="fk_order_item",
            foreignKeyDefinition = "FOREIGN KEY (ro_id) REFERENCES restaurant_order(ro_id) DEFERRABLE INITIALLY DEFERRED"))

    private RestaurantOrder restaurantOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id" , foreignKey = @ForeignKey(name="fk_menu_order_item",
            foreignKeyDefinition = "FOREIGN KEY (m_id) REFERENCES menu(m_id) DEFERRABLE INITIALLY DEFERRED"))

    private Menu menu;

    @Column(nullable = false)
    private int quantity;

    // Getters and setters
    public int getOrderitem_id() {
        return orderitem_id;
    }

    public void setOrderitem_id(int orderitemId) {
        this.orderitem_id = orderitemId;
    }

    public RestaurantOrder getRestaurantOrder() {
        return restaurantOrder;
    }

    public void setRestaurantOrder(RestaurantOrder restaurantOrder) {
        this.restaurantOrder = restaurantOrder;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menuItem) {
        this.menu = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
