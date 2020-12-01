package com.alex.poseidon.services;

import com.alex.poseidon.models.RatingModel;
import com.alex.poseidon.repositories.RatingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void saveUpdateFindDeleteRatingShouldPerformTheirActionsAndSucceed() {
        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        // Save
        rating = ratingRepository.save(rating);
        Assert.assertNotNull(rating.getId());
        Assert.assertTrue(rating.getOrderNumber() == 10);

        // Update
        rating.setOrderNumber(20);
        rating = ratingRepository.save(rating);
        Assert.assertTrue(rating.getOrderNumber() == 20);

        // Find
        List<RatingModel> listResult = ratingRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        RatingModel ratingResult = ratingRepository.findById(rating.getId());
        Assert.assertTrue(ratingResult.getOrderNumber() == 20);
        Assert.assertTrue(ratingResult.getSandPRating().equals("Sand PRating"));

        // Delete
        Integer id = rating.getId();
        ratingRepository.delete(rating);
        Optional<RatingModel> ratingList = ratingRepository.findById(id);
        Assert.assertFalse(ratingList.isPresent());
    }
}