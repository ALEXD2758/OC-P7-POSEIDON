package com.alex.poseidon.repositories;

import com.alex.poseidon.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
