package com.alex.poseidon.repositories;

import com.alex.poseidon.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
