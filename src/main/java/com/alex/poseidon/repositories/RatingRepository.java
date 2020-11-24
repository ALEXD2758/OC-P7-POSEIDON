package com.alex.poseidon.repositories;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.models.RatingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<RatingModel, Integer> {

    List<RatingModel> findAll();

    List<RatingModel> findAllById(Iterable<Integer> integers);

    RatingModel findById(int id);
}