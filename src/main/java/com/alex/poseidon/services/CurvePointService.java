package com.alex.poseidon.services;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.repositories.CurvePointRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CurvePointService {

    private CurvePointRepository curvePointRep;

    @Autowired
    public CurvePointService(CurvePointRepository curvePointRep) {
        this.curvePointRep = curvePointRep;
    }

    public List<CurvePointModel> getAllCurvePoints() {
        return curvePointRep.findAll();
    }

    public void saveCurvePoint(CurvePointModel curvePoint) {
        curvePointRep.save(curvePoint);
    }

    public boolean checkIfIdExists(int id) {
        return curvePointRep.existsById(id);
    }

    public void deleteCurvePointById(int id) {
        curvePointRep.deleteById(id);
    }

    public CurvePointModel getCurvePointById(int bidListId) {
        return curvePointRep.findById(bidListId);
    }

    /**
     * Get a timestamp for the field creationDate
     *
     * @return a timestamp of the current time and date
     */
    public LocalDateTime getDateForFieldCreationDate(){
        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(millis);

        return date;
    }
}