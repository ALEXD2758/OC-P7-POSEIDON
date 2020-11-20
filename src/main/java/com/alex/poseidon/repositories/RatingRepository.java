package com.alex.poseidon.repositories;

import com.alex.poseidon.models.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingModel, Integer> {

}
