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

    /**
     * Get a list of all bids
     *
     * @return list of BidListModel containing all bid models
     */
    public List<BidListModel> getAllBids() {
        return bidListRep.findAll();
    }

    /**
     *  Check if an Id already exists
     * @param id the bid ID
     * @return true if ID already exists
     * @return false if ID doesn't exist
     */
    public boolean checkIfIdExists(int id) {
        return bidListRep.existsById(id);
    }

    /**
     * Save a new Bid in the DB
     * @param bidList the BidListModel to save
     */
    public void saveBid(BidListModel bidList) {
        bidListRep.save(bidList);
    }

    /**
     * Delete an existent bid from the DB
     * @param bidListId the bid ID
     */
    public void deleteBidById(int bidListId) {
        bidListRep.deleteById(bidListId);
    }

    /**
     * Get a Bid model by ID
     * @param bidListId the bid ID
     * @return BidListModel found with the ID
     */
    public BidListModel getBidByBidListId(int bidListId) {
        return bidListRep.findByBidListId(bidListId);
    }
}