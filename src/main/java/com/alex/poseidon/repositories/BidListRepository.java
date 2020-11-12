package com.alex.poseidon.repositories;

import com.alex.poseidon.models.BidList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

    List<BidList> findAll();

    Page<BidList> findAll(Pageable pageable);

    List<BidList> findAllById(Iterable<Integer> integers);

    BidList findByBidListId(int bidListId);

}
