package com.alex.poseidon.controllers;

import com.alex.poseidon.models.RatingModel;
import com.alex.poseidon.services.RatingService;
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
public class RatingControllerITTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @MockBean
    RatingService ratingService;

    @Before
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRatingListViewShouldReturnSuccess() throws Exception {
        //1. Setup

        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        List<RatingModel> ratingList = new ArrayList<>();
        ratingList.add(rating);

        doReturn(ratingList)
                .when(ratingService)
                .getAllRatings();

        //2. Act
        mockMvc.perform(get("/rating/list"))
                //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("rating"))
                .andReturn();
        assertTrue(ratingList.get(0).getFitchRating().equals("Fitch Rating"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRatingAddViewShouldReturnSuccess() throws Exception {
        //1. Setup

        //2. Act
        mockMvc.perform(get("/rating/add"))
                //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestRatingValidateShouldReturnSuccess() throws Exception {
        //1. Setup

        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        List<RatingModel> ratingList = new ArrayList<>();
        ratingList.add(rating);

        doNothing()
                .when(ratingService)
                .saveRating(rating);

        doReturn(ratingList)
                .when(ratingService)
                .getAllRatings();
        //2. Act
        mockMvc.perform(post("/rating/validate")
                .flashAttr("successSaveMessage", "Your rating was successfully added")
                .param("id", "1")
                .param("moodysRating", "Moodys Rating")
                .param("sandPRating", "Sand PRating")
                .param("fitchRating", "Fitch Rating")
                .param("orderNumber", "10"))
                //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(flash().attributeExists("successSaveMessage"))
                .andReturn();
        assertTrue(ratingList.get(0).getFitchRating().equals("Fitch Rating"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRatingUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        doReturn(true)
                .when(ratingService)
                .checkIfIdExists(rating.getId());

        doReturn(rating)
                .when(ratingService)
                .getRatingById(rating.getId());
        //2. Act
        mockMvc.perform(get("/rating/update/{id}", "1"))
                //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"))
                .andReturn();
        assertTrue(rating.getFitchRating().equals("Fitch Rating"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestRatingUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        List<RatingModel> ratingList = new ArrayList<>();
        ratingList.add(rating);

        doReturn(true)
                .when(ratingService)
                .checkIfIdExists(rating.getId());

        doNothing()
                .when(ratingService)
                .saveRating(rating);

        doReturn(ratingList)
                .when(ratingService)
                .getAllRatings();

        //2. Act
        mockMvc.perform(post("/rating/update/{id}", "1")
                .flashAttr("successUpdateMessage", "Your rating was successfully updated")
                .param("id", "1")
                .param("moodysRating", "Moodys Rating")
                .param("sandPRating", "Sand PRating")
                .param("fitchRating", "Fitch Rating")
                .param("orderNumber", "10"))
                //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(flash().attributeExists("successUpdateMessage"))
                .andReturn();
        assertTrue(ratingList.get(0).getFitchRating().equals("Fitch Rating"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRatingDeleteIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RatingModel rating = new RatingModel();
        rating.setId(1);
        rating.setMoodysRating("Moodys Rating");
        rating.setSandPRating("Sand PRating");
        rating.setFitchRating("Fitch Rating");
        rating.setOrderNumber(10);

        List<RatingModel> ratingList = new ArrayList<>();
        ratingList.add(rating);

        doReturn(true)
                .when(ratingService)
                .checkIfIdExists(rating.getId());

        doNothing()
                .when(ratingService)
                .deleteRatingById(rating.getId());

        doReturn(ratingList)
                .when(ratingService)
                .getAllRatings();
        //2. Act
        mockMvc.perform(get("/rating/delete/{id}", "1")
                .flashAttr("successDeleteMessage", "Your rating was successfully deleted"))
                //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"))
                .andExpect(flash().attributeExists("successDeleteMessage"))
                .andReturn();
        assertTrue(ratingList.get(0).getFitchRating().equals("Fitch Rating"));
    }
}