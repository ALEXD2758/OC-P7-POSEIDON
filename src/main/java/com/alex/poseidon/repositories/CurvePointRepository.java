package com.alex.poseidon.repositories;

import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.models.CurvePointModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CurvePointRepository extends JpaRepository<CurvePointModel, Integer> {
    List<CurvePointModel> findAll();

    List<CurvePointModel> findAllById(Iterable<Integer> integers);

    CurvePointModel findById(int id);
}
