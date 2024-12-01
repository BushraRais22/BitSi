package org.example.scd_db_project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "chef_rating")
public class ChefRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefrating_seq_generator")
    @SequenceGenerator(name = "chefrating_seq_generator", sequenceName = "CHEFRATING_SEQ", allocationSize = 1)
    private int rating_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ch_id" , foreignKey = @ForeignKey(name="fk_chef_rating",
            foreignKeyDefinition = "FOREIGN KEY (ch_id) REFERENCES chef(ch_id) DEFERRABLE INITIALLY DEFERRED"))

    private Chef chef;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id" , foreignKey = @ForeignKey(name="fk_customer_chef_rating",
            foreignKeyDefinition = "FOREIGN KEY (c_id) REFERENCES customer(c_id) DEFERRABLE INITIALLY DEFERRED"))

    private Customer customer;

    @Temporal(TemporalType.DATE)

    @Column(nullable = false)
    private Date cr_date;

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

    public Date getCr_date() {
        return cr_date;
    }

    public void setCr_date(Date date) {
        this.cr_date = date;
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
