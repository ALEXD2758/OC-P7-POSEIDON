package com.alex.poseidon.services;

import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private BidListRepository bidListRep;

    @Autowired
    public BidListService(BidListRepository bidListRep) {
        this.bidListRep = bidListRep;
    }

    public boolean checkIfBidListIdExists(Integer bidListId) {
        return bidListRep.existsById(bidListId);
    }
    
    public List<BidListModel> getAllBids() {
        return bidListRep.findAll();
    }

    public void saveBid(BidListModel bidList) {
        bidListRep.save(bidList);
    }

    public void deleteBidById(Integer bidListId) {
        bidListRep.deleteById(bidListId);
    }

    public void getBidByBidListId(Integer bidListId) {
        bidListRep.findByBidListId(bidListId);
        }
}
