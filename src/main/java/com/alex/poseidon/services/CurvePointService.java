package com.alex.poseidon.services;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.repositories.CurvePointRepository;
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

    public boolean checkIfCurvePointIdExists(int id) {
        return curvePointRep.existsById(id);
    }
    
    public List<CurvePointModel> getAllCurvePoints() {
        return curvePointRep.findAll();
    }

    public void saveCurvePoint(CurvePointModel curvePoint) {
        curvePointRep.save(curvePoint);
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
    public Timestamp getTimestampForFieldCreationDate(){
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
        return ts;
    }
}