package org.example.scd_db_project.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class RestaurantMenuId implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idd" , foreignKey = @ForeignKey(name="fk_restaurant_menu_id",
            foreignKeyDefinition = "FOREIGN KEY (idd) REFERENCES restaurant(idd) DEFERRABLE INITIALLY DEFERRED"))

    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id" , foreignKey = @ForeignKey(name="fk_menu_restaurant_menu_id",
            foreignKeyDefinition = "FOREIGN KEY (id) REFERENCES menu(id) DEFERRABLE INITIALLY DEFERRED"))
    private Menu menu;

    public RestaurantMenuId() {
    }

    public RestaurantMenuId(Restaurant restaurant, Menu menu) {
        this.restaurant = restaurant;
        this.menu = menu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) { //to check if two entities are the same in terms of business logic (not just by their memory reference).
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantMenuId that = (RestaurantMenuId) o;
        if (restaurant != null ? !restaurant.equals(that.restaurant) : that.restaurant != null)
            return false;
        return menu != null ? menu.equals(that.menu) : that.menu == null;
    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }
}
//The hashCode() method provides a way of computing a small integer hash code from the
// information in your object. This hash code is used by hash-based collections to quickly locate
// objects without needing to check every element.

//Initial hash code computation: Starts with a hash code derived from the restaurant object.
//
//If restaurant is null, it uses 0; otherwise, it calls restaurant.hashCode().
//Combine hash codes:
//
//The hash code is combined with the hash code of the menu object, using 31 as a multiplier.
//The number 31 is a small prime number and is commonly used because it reduces the chance of
// generating colliding hash codes for different objects. It ensures a good distribution of hash
// values.
