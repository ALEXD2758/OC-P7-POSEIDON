package com.alex.poseidon.services;

import com.alex.poseidon.models.BidList;
import com.alex.poseidon.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BidListService {

    private BidListRepository bidListRep;

    @Autowired
    public BidListService(BidListRepository bidListRep) {
        this.bidListRep = bidListRep;
    }

    public boolean checkIfBidListIdExists(Integer bidListId) {
        return bidListRep.existsById(bidListId);
    }
    
    public List<BidList> getAllBids() {
        return bidListRep.findAll();
    }

    public void saveBid(BidList bidList) {
        bidListRep.save(bidList);
    }

    public void deleteBidById(Integer bidListId) {
        bidListRep.deleteById(bidListId);
    }

    public void getBidByBidListId(Integer bidListId) {
        bidListRep.findByBidListId(bidListId);
        }
}
