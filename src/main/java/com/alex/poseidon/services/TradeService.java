package com.alex.poseidon.services;

import com.alex.poseidon.models.TradeModel;
import com.alex.poseidon.repositories.TradeRepository;
import org.joda.time.LocalDate;
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
     * Get a timestamp for the field creationDate
     *
     * @return a timestamp of the current time and date
     */
    public LocalDate getCreationDateForDateFields() {
        long millis=System.currentTimeMillis();
        LocalDate date = new LocalDate(millis);

        return date;
    }
}
