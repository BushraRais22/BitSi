package org.example.scd_db_project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "restaurant_order")
public class RestaurantOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resorder_seq_generator")
    @SequenceGenerator(name = "resorder_seq_generator", sequenceName = "RESORDER_SEQ", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private double total_amount;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date ro_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id" , foreignKey = @ForeignKey(name="fk_res_order",
            foreignKeyDefinition = "FOREIGN KEY (r_id) REFERENCES restaurant(r_id) DEFERRABLE INITIALLY DEFERRED"))

    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id" , foreignKey = @ForeignKey(name="fk_customer_res_order",
            foreignKeyDefinition = "FOREIGN KEY (c_id) REFERENCES customer(c_id) DEFERRABLE INITIALLY DEFERRED"))

    private Customer customer;

    @Column(length = 500)
    private String specifications;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int restaurantOrderId) {
        this.id = restaurantOrderId;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double totalAmount) {
        this.total_amount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRo_date() {
        return ro_date;
    }

    public void setRo_date(Date orderDate) {
        this.ro_date = orderDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
