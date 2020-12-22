package com.alex.poseidon.repositories;

import com.alex.poseidon.models.BidListModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidListRepository extends JpaRepository<BidListModel, Integer> {

    List<BidListModel> findAll();

    BidListModel findByBidListId(int bidListId);

    boolean existsByBidListId(int bidListId);
}