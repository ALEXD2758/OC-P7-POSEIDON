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

    public List<BidListModel> getAllBids() {
        return bidListRep.findAll();
    }

    public boolean checkIfIdExists(int id) {
        return bidListRep.existsById(id);
    }

    public void saveBid(BidListModel bidList) {
        bidListRep.save(bidList);
    }

    public void deleteBidById(int bidListId) {
        bidListRep.deleteById(bidListId);
    }

    public BidListModel getBidByBidListId(int bidListId) {
        return bidListRep.findByBidListId(bidListId);
    }
}