package com.alex.poseidon.controllers;

import com.alex.poseidon.models.RatingModel;
import com.alex.poseidon.services.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RatingController {

    private static final Logger logger = LogManager.getLogger("RatingController");

    @Autowired
    RatingService ratingService;
    /**
     * Render the view rating/list
     * Adds attribute rating to the model, containing all ratings available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "rating/list", returning the associated view
     * with attribute
     */
    @GetMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("rating", ratingService.getAllRatings());
        logger.info("GET /rating/list : OK");
        return "rating/list";
    }

    /**
     * Render the view rating/add
     * Adds attribute rating to the model, containing a new RatingModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "rating/add", returning the associated view
     * with attribute
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new RatingModel());
        logger.info("GET /rating/add : OK");
        return "rating/add";
    }

    /**
     * Save new rating to the table rating if Bindingresult has no errors
     * Add Flash Attribute with success message
     * Add attribute rating to the model, containing all ratings available in DB
     *
     * @param rating the RatingModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "rating/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") RatingModel rating, BindingResult result, Model model,
                           RedirectAttributes ra) {
        if (!result.hasErrors()) {
            ratingService.saveRating(rating);
            ra.addFlashAttribute("successSaveMessage", "Your rating was successfully added");
            model.addAttribute("rating", ratingService.getAllRatings());

            logger.info("POST /rating/validate : OK");
            return "redirect:/rating/list";
        }
        logger.info("POST /rating/validate : NOK");
        return "rating/add";
    }

    /**
     * Render the view rating/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute rating to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "rating/update", returning the associated view
     * with attribute (if no Exception)
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            if (ratingService.checkIfIdExists(id) == false) {
                logger.info("GET /rating/update : Non existent id");
                return "redirect:/rating/list";
            }
            model.addAttribute("rating", ratingService.getRatingById(id));
            logger.info("GET /rating/update : OK");
        } catch (Exception e) {
            logger.info("GET /rating/delete : NOK " + "Invalid rating ID " + id);
        }
        return "rating/update";
    }

    /**
     * Update existing rating to the table rating if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute rating to the model, containing all ratings available in DB
     *
     * @param rating the RatingModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") int id,
                               @Valid @ModelAttribute("rating") RatingModel rating,
                               BindingResult result, Model model, RedirectAttributes ra) {
        if (ratingService.checkIfIdExists(id) == false) {
            logger.info("GET /rating/update : Non existent id");
            return "redirect:/rating/list";
        }
        if (result.hasErrors()) {
            logger.info("POST /rating/update : NOK");
            return "/rating/list";
        }
        ratingService.saveRating(rating);
        ra.addFlashAttribute("successUpdateMessage", "Your rating was successfully updated");
        model.addAttribute("rating", ratingService.getAllRatings());

        logger.info("POST /rating/update : OK");
        return "redirect:/rating/list";
    }

    /**
     * Delete existing rating from the table rating
     * Add Flash Attribute with success message
     * Add attribute rating to the model, containing all ratings available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "rating/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            if (ratingService.checkIfIdExists(id) == false) {
                logger.info("GET /rating/delete : Non existent id");
                return "redirect:/rating/list";
            }
            ratingService.deleteRatingById(id);
            model.addAttribute("rating", ratingService.getAllRatings());
            ra.addFlashAttribute("successDeleteMessage", "This rating was successfully deleted");

            logger.info("/rating/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the rating");
            logger.info("/rating/delete : NOK " + "Invalid rating ID " + id);
        }
        return "redirect:/rating/list";
    }
}