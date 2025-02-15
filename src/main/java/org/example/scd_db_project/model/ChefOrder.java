package org.example.scd_db_project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "chef_order")
public class ChefOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheforder_seq_generator")
    @SequenceGenerator(name = "cheforder_seq_generator", sequenceName = "CHEFORDER_SEQ", allocationSize = 1)
    private int co_id;

    @Column(nullable = false)
    private double total_amount;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date co_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id" , foreignKey = @ForeignKey(name="fk_chef_order",
            foreignKeyDefinition = "FOREIGN KEY (id) REFERENCES chef(ch_id) DEFERRABLE INITIALLY DEFERRED"))

    private Chef chef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id" , foreignKey = @ForeignKey(name="fk_customer_chef_order",
            foreignKeyDefinition = "FOREIGN KEY (c_id) REFERENCES customer(c_id) DEFERRABLE INITIALLY DEFERRED"))

    private Customer customer;

    @Column(length = 500)
    private String specifications;

    // Getters and setters
    public int getCo_id() {
        return co_id;
    }

    public void setCo_id(int restaurantOrderId) {
        this.co_id = restaurantOrderId;
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

    public Date getCo_date() {
        return co_date;
    }

    public void setCo_date(Date orderDate) {
        this.co_date = orderDate;
    }


    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
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
