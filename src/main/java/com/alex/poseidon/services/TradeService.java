package com.alex.poseidon.services;

import com.alex.poseidon.models.TradeModel;
import com.alex.poseidon.repositories.TradeRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private TradeRepository tradeRep;

    @Autowired
    public TradeService(TradeRepository tradeRep) {
        this.tradeRep = tradeRep;
    }

    public boolean checkIfTradeIdExists(int id) {
        return tradeRep.existsById(id);
    }

    public List<TradeModel> getAllTrades() {
        return tradeRep.findAll();
    }

    public void saveTrade(TradeModel trade) {
        tradeRep.save(trade);
    }

    public void deleteTradeById(int id) {
        tradeRep.deleteById(id);
    }

    public TradeModel getTradeById(int id) {
        return tradeRep.findById(id);
    }
    /**
     * Get a LocalDateTime for the field creationDate
     *
     * @return a LocalDateTime of the current time and date
     */
    public LocalDateTime getCreationDateForDateFields() {
        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(millis);

        return date;
    }
}