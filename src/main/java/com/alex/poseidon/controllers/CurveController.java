package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.CurveControllerInterface;
import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.services.BidListService;
import com.alex.poseidon.services.CurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CurveController implements CurveControllerInterface {

    private static final Logger logger = LogManager.getLogger("CurveController");

    @Autowired
    CurvePointService curvePointService;

    /**
     * Render the view curvePoint/list
     * Adds attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/list", returning the associated view
     * with attribute
     */
    @Override
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());
        logger.info("curvePoint/list : OK");
        return "curvePoint/list";
    }

    /**
     * Render the view curvePoint/add
     * Adds attribute CurvePoint to the model, containing a new CurvePointModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/add", returning the associated view
     * with attribute
     */
    @Override
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {
        model.addAttribute("curvePoint", new CurvePointModel());
        logger.info("GET /curvePoint/add : OK");
        return "curvePoint/add";
    }

    /**
     * Save new Curve Point to the table curvepoint if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param curvePoint the CurvePointModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "curvePoint/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @Override
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointModel curvePoint, BindingResult result,
                           Model model, RedirectAttributes ra) {
        if (!result.hasErrors()) {
            curvePoint.setCreationDate(curvePointService.getTimestampForFieldCreationDate());
            curvePointService.saveCurvePoint(curvePoint);
            ra.addFlashAttribute("successSaveMessage", "Your curve point was successfully added");
            model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());

            logger.info("POST /curvePoint/validate : OK");
            return "redirect:/curvePoint/list";
        }
        logger.info("/curvePoint/validate : NOK");
        return "curvePoint/add";
    }

    /**
     * Render the view curvePoint/update with the chosen id in a model attribute
     * with the associated data of the chosen id
     * Add attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param id the int of the id chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "curvePoint/update", returning the associated view
     * with attribute (if no Exception)
     */
    @Override
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("curvePoint", curvePointService.getCurvePointById(id));
            logger.info("GET /curvePoint/update : OK");
        } catch (Exception e) {
            logger.info("/curvePoint/delete : NOK " + "Invalid curve point ID " + id);
            throw new IllegalArgumentException("Invalid curve point ID " + id);
        }
        return "curvePoint/update";
    }

    /**
     * Update existing Curve Point to the table curvepoint if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param curvePoint the CurvePointModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes
     */
    @Override
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") int id,
                                   @Valid @ModelAttribute("curvePoint") CurvePointModel curvePoint,
                            BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            logger.info("POST /curvePoint/update : NOK");
            return "/curvePoint/list";
        }
        curvePoint.setCreationDate(curvePointService.getTimestampForFieldCreationDate());
        curvePointService.saveCurvePoint(curvePoint);
        ra.addFlashAttribute("successUpdateMessage", "Your curve point was successfully updated");
        model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());

        logger.info("POST /curvePoint/update : OK");
        return "redirect:/curvePoint/list";
    }

    /**
     * Delete existing Curve Point from the table curvepoint
     * Add Flash Attribute with success message
     * Add attribute CurvePoint to the model, containing all curve points available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "curvePoint/list", returning the associated view,
     * with attributes
     */
    @Override
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            curvePointService.deleteCurvePointById(id);
            model.addAttribute("curvePoint", curvePointService.getAllCurvePoints());
            ra.addFlashAttribute("successDeleteMessage", "This curve point was successfully deleted");

            logger.info("/curvePoint/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the curve point");
            logger.info("/curvePoint/delete : NOK " + "Invalid curve point ID " + id);
            throw new IllegalArgumentException("Invalid curve point ID " + id);
        }
        return "redirect:/curvePoint/list";
    }
}
