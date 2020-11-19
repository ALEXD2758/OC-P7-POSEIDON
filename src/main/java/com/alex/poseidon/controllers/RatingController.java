package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.RatingControllerInterface;
import com.alex.poseidon.models.RatingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController implements RatingControllerInterface {
    // TODO: Inject Rating service

    /**
     * Render the view bidList/list
     * Adds attribute BidList to the model, containing all Bids available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "bidList/list", returning the associated view
     * with attribute
     */
    @Override
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        return "rating/list";
    }

    /**
     * Render the view bidList/add
     * Adds attribute BidList to the model, containing a new BidMidListModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "bidList/add", returning the associated view
     * with attribute
     */
    @Override
    @GetMapping("/rating/add")
    public String addRatingForm(RatingModel rating) {
        return "rating/add";
    }

    /**
     * Save new Bid to the table bidlist if Bindingresult has no errors
     * Add Flash Attribute with success message
     * Add attribute BidList to the model, containing all Bids available in DB
     *
     * @param bid the BidListModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect scenarios
     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "bidList/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @Override
    @PostMapping("/rating/validate")
    public String validate(@Valid RatingModel rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        return "rating/add";
    }

    /**
     * Render the view bidList/update with the chosen bidListId in a model attribute
     * with the associated data of the chosen ID
     * Add attribute BidList to the model, containing all Bids available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "bidList/update", returning the associated view
     * with attribute (if no Exception)
     */
    @Override
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        return "rating/update";
    }

    /**
     * Update existing Bid to the table bidlist if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute BidList to the model, containing all Bids available in DB
     *
     * @param bidList the BidListModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes
     */
    @Override
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid RatingModel rating,
                               BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    /**
     * Delete existing Bid from the table bidlist
     * Add Flash Attribute with success message
     * Add attribute BidList to the model, containing all Bids available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "bidList/list", returning the associated view,
     * with attributes
     */
    @Override
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}
