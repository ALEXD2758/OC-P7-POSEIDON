package com.alex.poseidon.services;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.repositories.CurvePointRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    private CurvePointRepository curvePointRep;

    @Autowired
    public CurvePointService(CurvePointRepository curvePointRep) {
        this.curvePointRep = curvePointRep;
    }

    /**
     * Get a list of all curve points
     *
     * @return list of CurvePointModel containing all curve point models
     */
    public List<CurvePointModel> getAllCurvePoints() {
        return curvePointRep.findAll();
    }

    /**
     * Save a new curve point in the DB
     * @param curvePoint the CurvePointModel to save
     */
    public void saveCurvePoint(CurvePointModel curvePoint) {
        curvePointRep.save(curvePoint);
    }

    /**
     * Check if an Id already exists
     * @param id the curve point ID
     * @return true if ID already exists
     * @return false if ID doesn't exist
     */
    public boolean checkIfIdExists(int id) {
        return curvePointRep.existsById(id);
    }

    /**
     * Delete an existent Curve Point from the DB
     * @param id the curve point ID
     */
    public void deleteCurvePointById(int id) {
        curvePointRep.deleteById(id);
    }

    /**
     * Get a Curve Point model by ID
     * @param curvePointId the curve point ID
     * @return CurvePointModel found with the ID
     */
    public CurvePointModel getCurvePointById(int curvePointId) {
        return curvePointRep.findById(curvePointId);
    }

    /**
     * Get a LocalDateTime for the field creationDate
     *
     * @return a LocalDateTime of the current time and date
     */
    public LocalDateTime getDateForFieldCreationDate(){
        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(millis);

        return date;
    }
}