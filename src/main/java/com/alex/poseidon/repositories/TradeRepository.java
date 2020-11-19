package com.alex.poseidon.repositories;

import com.alex.poseidon.models.TradeModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<TradeModel, Integer> {
}
