package com.alex.poseidon.services;

import com.alex.poseidon.models.RatingModel;
import com.alex.poseidon.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRep;

    @Autowired
    public RatingService(RatingRepository ratingRep) {
        this.ratingRep = ratingRep;
    }

    /**
     * Get a list of all ratings
     *
     * @return list of RatingModel containing all rating models
     */
    public List<RatingModel> getAllRatings() {
        return ratingRep.findAll();
    }

    /**
     *  Check if an Id already exists
     * @param id the rating ID
     * @return true if ID already exists
     * @return false if ID doesn't exist
     */
    public boolean checkIfIdExists(int id) {
        return ratingRep.existsById(id);
    }

    /**
     * Save a new rating in the DB
     * @param rating the BidListModel to save
     */
    public void saveRating(RatingModel rating) {
        ratingRep.save(rating);
    }

    /**
     * Delete an existent rating from the DB
     * @param id the rating ID
     */
    public void deleteRatingById(int id) {
        ratingRep.deleteById(id);
    }

    /**
     * Get a rating model by ID
     * @param id the rating ID
     * @return RatingModel found with the ID
     */
    public RatingModel getRatingById(int id) {
        return ratingRep.findById(id);
    }
}