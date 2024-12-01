package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_menu")
public class RestaurantMenu {
    @EmbeddedId
    private RestaurantMenuId id;

    @Column(nullable = false)
    private double rm_price;

    @Column(nullable = false)
    private boolean rm_availability;

    // Getters and setters for id, rm_price, rm_availability
    public RestaurantMenuId getId() {
        return id;
    }

    public void setId(RestaurantMenuId id) {
        this.id = id;
    }

    public double getRm_price() {
        return rm_price;
    }

    public void setRm_price(double rmPrice) {
        this.rm_price = rmPrice;
    }

    public boolean isRm_availability() {
        return rm_availability;
    }

    public void setRm_availability(boolean rmAvailability) {
        this.rm_availability = rmAvailability;
    }
}
