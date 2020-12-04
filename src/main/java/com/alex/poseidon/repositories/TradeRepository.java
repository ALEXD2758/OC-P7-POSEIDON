package com.alex.poseidon.repositories;

import com.alex.poseidon.models.RuleNameModel;
import com.alex.poseidon.models.TradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TradeRepository extends JpaRepository<TradeModel, Integer> {
    List<TradeModel> findAll();

    TradeModel findById(int id);
}