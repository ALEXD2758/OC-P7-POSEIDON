package com.alex.poseidon.repositories;

import com.alex.poseidon.models.BidListModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BidListRepository extends JpaRepository<BidListModel, Integer> {

    List<BidListModel> findAll();

    //To probable later implementation
    // Page<BidListModel> findAll(Pageable pageable);

    List<BidListModel> findAllById(Iterable<Integer> integers);

    BidListModel findByBidListId(int bidListId);

}
