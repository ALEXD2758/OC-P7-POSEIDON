package com.alex.poseidon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rating")
public class RatingModel {

    @Id
    @Column(name = "id")
    private int id;
    @Size(max=125, message = "The size of moodys Rating must be of maximum 125 characters")
    @Column(name = "moodys_rating")
    private String moodysRating;
    @Size(max=125, message = "The size of sand PRating must be of maximum 125 characters")
    @Column(name = "sand_p_rating")
    private String sandPRating;
    @Size(max=125, message = "The size of fitch Rating must be of maximum 125 characters")
    @Column(name = "fitch_rating")
    private String fitchRating;
    @NotNull(message= "Order Number is mandatory")
    @Column(name = "order_number")
    private int orderNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}