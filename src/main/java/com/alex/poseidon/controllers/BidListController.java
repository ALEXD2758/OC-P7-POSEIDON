package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.BidListControllerInterface;
import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.repositories.BidListRepository;
import com.alex.poseidon.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class BidListController implements BidListControllerInterface {

    private static final Logger logger = LogManager.getLogger("CurveController");

    @Autowired
    BidListService bidListService;

    /**
     * Render the view bidList/list
     * Adds attribute BidList to the model, containing all Bids available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "bidList/list", returning the associated view
     * with attribute
     */
    @Override
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidList", bidListService.getAllBids());
        logger.info("bidList/list : OK");
        return "bidList/list";
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
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidListModel());
        logger.info("GET /bidList/add : OK");
        return "bidList/add";
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
    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListModel bid, BindingResult result, Model model,
                           RedirectAttributes ra) {
        if (!result.hasErrors()) {
            bidListService.saveBid(bid);
            ra.addFlashAttribute("successSaveMessage", "Your bid was successfully added");
            model.addAttribute("bidList", bidListService.getAllBids());

            logger.info("POST /bidList/validate : OK");
            return "redirect:/bidList/list";
        }
        logger.info("/bidList/validate : NOK");
        return "bidList/add";
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
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("bidList", bidListService.getBidByBidListId(id));
            logger.info("GET /bidList/update : OK");
        } catch (Exception e) {
            logger.info("/bidList/delete : NOK " + "Invalid bidList ID " + id);
            throw new IllegalArgumentException("Invalid bidlist Id" + id);
        }
        return "bidList/update";
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
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid @ModelAttribute("bidList") BidListModel bidList,
                            BindingResult result, Model model, RedirectAttributes ra) {
        if (!result.hasErrors()) {
            bidListService.saveBid(bidList);
            ra.addFlashAttribute("successUpdateMessage", "Your bid was successfully updated");
            model.addAttribute("bidList", bidListService.getAllBids());

            logger.info("POST /bidList/update : OK");
            return "redirect:/bidList/list";
        }
        logger.info("POST /bidList/update : NOK");
        return "/bidList/add";
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
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            bidListService.deleteBidById(id);
            model.addAttribute("bidList", bidListService.getAllBids());
            ra.addFlashAttribute("successDeleteMessage", "This bid was successfully deleted");

            logger.info("/bidList/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the bid");
            logger.info("/bidList/delete : NOK " + "Invalid bidList ID " + id);
            throw new IllegalArgumentException("Invalid bid Id" + id);
        }
        return "redirect:/bidList/list";
    }
}
