package com.alex.poseidon.services;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.repositories.CurvePointRepository;
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
public class CurvePointTests {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void saveUpdateFindDeleteShouldPerformTheirActionsAndSucceed() {
        long millis=System.currentTimeMillis();
        java.util.Date date = new java.util.Date(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertNotNull(curvePoint.getId());
        Assert.assertTrue(curvePoint.getCurveId() == 2);
        Assert.assertTrue(curvePoint.getValue() == 35D);

        // Update
        curvePoint.setCurveId(20);
        curvePoint.setValue(40D);
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertTrue(curvePoint.getCurveId() == 20);
        Assert.assertTrue(curvePoint.getValue() == 40D);

        // Find
        List<CurvePointModel> listResult = curvePointRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        CurvePointModel curveResult = curvePointRepository.findById(curvePoint.getId());
        Assert.assertTrue(curveResult.getCurveId() == 20);
        Assert.assertTrue(curveResult.getValue() == 40D);

        // Delete
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePointModel> curvePointList = curvePointRepository.findById(id);
        Assert.assertFalse(curvePointList.isPresent());
    }
}