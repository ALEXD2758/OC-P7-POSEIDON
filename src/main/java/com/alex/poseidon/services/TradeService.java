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

    /**
     *  Check if an Id already exists
     * @param id the trade ID
     * @return true if ID already exists
     * @return false if ID doesn't exist
     */
    public boolean checkIfTradeIdExists(int id) {
        return tradeRep.existsById(id);
    }

    /**
     * Get a list of all trades
     *
     * @return list of TradeModel containing all trade models
     */
    public List<TradeModel> getAllTrades() {
        return tradeRep.findAll();
    }

    /**
     * Save a new trade in the DB
     * @param trade the TradeModel to save
     */
    public void saveTrade(TradeModel trade) {
        tradeRep.save(trade);
    }

    /**
     * Delete an existent trade from the DB
     * @param id the trade ID
     */
    public void deleteTradeById(int id) {
        tradeRep.deleteById(id);
    }

    /**
     * Get a trade model by ID
     * @param id the trade ID
     * @return TradeModel found with the ID
     */
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