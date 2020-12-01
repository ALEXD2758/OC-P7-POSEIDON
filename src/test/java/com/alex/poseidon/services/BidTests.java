package com.alex.poseidon.services;

import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void saveUpdateFindDeleteBidShouldPerformTheirActionsAndSucceed() {
        BidListModel bid = new BidListModel();
        bid.setBidListId(1);
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10);

        // Save
        bid = bidListRepository.save(bid);
        Assert.assertNotNull(bid.getBidListId());
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

        // Find
        List<BidListModel> listResult = bidListRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        BidListModel bidModel = bidListRepository.findByBidListId(bid.getBidListId());
        Assert.assertEquals(bidModel.getAccount(), "Account Test", "Account Test");

        // Delete
        int id = bid.getBidListId();
        bidListRepository.delete(bid);
        Optional<BidListModel> BidListModel = bidListRepository.findById(id);
        Assert.assertFalse(BidListModel.isPresent());
    }
}