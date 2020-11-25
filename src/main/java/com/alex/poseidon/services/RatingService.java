package com.alex.poseidon.services;

import com.alex.poseidon.models.RatingModel;
import com.alex.poseidon.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RatingService {

    private RatingRepository ratingRep;

    @Autowired
    public RatingService(RatingRepository ratingRep) {
        this.ratingRep = ratingRep;
    }

    public boolean checkIfRatingIdExists(int id) {
        return ratingRep.existsById(id);
    }
    
    public List<RatingModel> getAllRatings() {
        return ratingRep.findAll();
    }

    public void saveRating(RatingModel rating) {
        ratingRep.save(rating);
    }

    public void deleteRatingById(int id) {
        ratingRep.deleteById(id);
    }

    public RatingModel getRatingById(int id) {
        return ratingRep.findById(id);
    }
}
