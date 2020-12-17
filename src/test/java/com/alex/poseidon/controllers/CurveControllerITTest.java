package com.alex.poseidon.controllers;

import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.services.CurvePointService;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurveControllerITTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @MockBean
    CurvePointService curvePointService;

    @Before
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestCurvePointListViewShouldReturnSuccess() throws Exception {
        //1. Setup

        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        List<CurvePointModel> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);

        doReturn(curvePointList)
                .when(curvePointService)
                .getAllCurvePoints();

        //2. Act
        mockMvc.perform(get("/curvePoint/list"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoint"))
                .andReturn();
        assertTrue(curvePointList.get(0).getCurveId() == 2);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestBidListAddViewShouldReturnSuccess() throws Exception {
        //1. Setup

        //2. Act
        mockMvc.perform(get("/curvePoint/add"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(model().attributeExists("curvePoint"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestBidListValidateShouldReturnSuccess() throws Exception {
        //1. Setup

        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        List<CurvePointModel> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);

        doNothing()
                .when(curvePointService)
                .saveCurvePoint(curvePoint);

        doReturn(curvePointList)
                .when(curvePointService)
                .getAllCurvePoints();
        //2. Act
        mockMvc.perform(post("/curvePoint/validate")
                 .flashAttr("successSaveMessage", "Your curve point was successfully added")
                .param("id", "10")
                .param("curveId", "2")
                .param("term", "20D")
                .param("value", "35D"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(flash().attributeExists("successSaveMessage"))
                .andReturn();
        assertTrue(curvePointList.get(0).getCurveId() == 2);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestBidListUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        doReturn(true)
                .when(curvePointService)
                .checkIfIdExists(curvePoint.getId());

        doReturn(curvePoint)
                .when(curvePointService)
                .getCurvePointById(curvePoint.getId());
        //2. Act
        mockMvc.perform(get("/curvePoint/update/{id}", "10"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"))
                .andReturn();
        assertTrue(curvePoint.getCurveId() == 2);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestBidListUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        List<CurvePointModel> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);

        doReturn(true)
                .when(curvePointService)
                .checkIfIdExists(curvePoint.getId());

        doNothing()
                .when(curvePointService)
                .saveCurvePoint(curvePoint);

        doReturn(curvePointList)
                .when(curvePointService)
                .getAllCurvePoints();

        //2. Act
        mockMvc.perform(post("/curvePoint/update/{id}", "10")
                .flashAttr("successUpdateMessage", "Your curve point was successfully updated")
                .param("id", "10")
                .param("curveId", "2")
                .param("term", "20D")
                .param("value", "35D"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(flash().attributeExists("successUpdateMessage"))
                .andReturn();
        assertTrue(curvePointList.get(0).getCurveId() == 2);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestBidListDeleteIdShouldReturnSuccess() throws Exception {
        //1. Setup

        long millis=System.currentTimeMillis();
        LocalDateTime date = new LocalDateTime(2024/01/01);

        CurvePointModel curvePoint = new CurvePointModel();
        curvePoint.setId(10);
        curvePoint.setCurveId(2);
        curvePoint.setTerm(20D);
        curvePoint.setValue(35D);
        curvePoint.setCreationDate(date);

        List<CurvePointModel> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint);

        doReturn(true)
                .when(curvePointService)
                .checkIfIdExists(curvePoint.getId());

        doNothing()
                .when(curvePointService)
                .deleteCurvePointById(curvePoint.getId());

        doReturn(curvePointList)
                .when(curvePointService)
                .getAllCurvePoints();
        //2. Act
        mockMvc.perform(get("/curvePoint/delete/{id}", "10")
                .flashAttr("successDeleteMessage", "Your curve point was successfully deleted"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/curvePoint/list"))
                .andExpect(flash().attributeExists("successDeleteMessage"))
                .andReturn();
        assertTrue(curvePointList.get(0).getCurveId() == 2);
    }
}