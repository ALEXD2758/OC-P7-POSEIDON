package com.alex.poseidon.repositories;

import com.alex.poseidon.models.CurvePointModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurvePointRepository extends JpaRepository<CurvePointModel, Integer> {
    List<CurvePointModel> findAll();

    CurvePointModel findById(int id);

    boolean existsById(int id);
}