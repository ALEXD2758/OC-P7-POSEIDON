package com.alex.poseidon.services;

import com.alex.poseidon.models.TradeModel;
import com.alex.poseidon.repositories.TradeRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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
public class TradeTests {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void saveUpdateFindDeleteTradeShouldPerformTheirActionsAndSucceed() {

        long millis= 1706858478726L;
        LocalDateTime date = new LocalDateTime(millis);
        LocalDateTime date1 = new LocalDateTime(1706858478726L);

        TradeModel trade = new TradeModel();
        trade.setTradeId(28);
        trade.setAccount("Trade Account");
        trade.setType("Type");
        trade.setCreationName("Creation Name");
        trade.setTradeDate(date);
        trade.setCreationDate(date1);

        // Save
        trade = tradeRepository.save(trade);
        Assert.assertNotNull(trade.getTradeId());
        Assert.assertTrue(trade.getAccount().equals("Trade Account"));
        Assert.assertTrue(trade.getCreationDate().compareTo(date1) == 0);

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeRepository.save(trade);
        Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

        // Find
        List<TradeModel> listResult = tradeRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        TradeModel tradeResult = tradeRepository.findById(trade.getTradeId());
        Assert.assertTrue(tradeResult.getAccount().equals("Trade Account Update"));
        Assert.assertTrue(tradeResult.getType().equals("Type"));

        // Delete
        Integer id = trade.getTradeId();
        tradeRepository.delete(trade);
        Optional<TradeModel> tradeList = tradeRepository.findById(id);
        Assert.assertFalse(tradeList.isPresent());
    }
}