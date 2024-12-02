package org.example.scd_db_project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "restaurant_rating")
public class RestaurantRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resrating_seq_generator")
    @SequenceGenerator(name = "resrating_seq_generator", sequenceName = "RESRATING_SEQ", allocationSize = 1)
        private int rating_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id" , foreignKey = @ForeignKey(name="fk_res_rating",
            foreignKeyDefinition = "FOREIGN KEY (r_id) REFERENCES restaurant(idd) DEFERRABLE INITIALLY DEFERRED"))

    private Restaurant restaurant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id" , foreignKey = @ForeignKey(name="fk_customer_res_rating",
            foreignKeyDefinition = "FOREIGN KEY (c_id) REFERENCES customer(c_id) DEFERRABLE INITIALLY DEFERRED"))
    private Customer customer;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date rr_date;

    @Column(nullable = false)
    private int rating_score;

    @Column(length = 500)
    private String review;

    // Getters and setters
    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
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

    public Date getRr_date() {
        return rr_date;
    }

    public void setRr_date(Date date) {
        this.rr_date = date;
    }

    public int getRating_score() {
        return rating_score;
    }

    public void setRating_score(int ratingScore) {
        this.rating_score = ratingScore;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
