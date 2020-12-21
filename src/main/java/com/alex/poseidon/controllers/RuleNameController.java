package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.RuleNameControllerInterface;
import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.models.RuleNameModel;
import com.alex.poseidon.services.CurvePointService;
import com.alex.poseidon.services.RuleNameService;
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
public class RuleNameController implements RuleNameControllerInterface {

    private static final Logger logger = LogManager.getLogger("RuleNameController");

    @Autowired
    RuleNameService ruleNameService;

    /**
     * Render the view ruleName/list
     * Adds attribute ruleName to the model, containing all rule names available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "ruleName/list", returning the associated view
     * with attribute
     */
    @Override
    @GetMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
        logger.info("GET /ruleName/list : OK");
        return "ruleName/list";
    }

    /**
     * Render the view ruleName/add
     * Adds attribute ruleName to the model, containing a new RuleNameModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/add", returning the associated view
     * with attribute
     */
    @Override
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleNameModel());
        logger.info("GET /ruleName/add : OK");
        return "ruleName/add";
    }

    /**
     * Save new rule name to the table ruleName if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute ruleName to the model, containing all rule names available in DB
     *
     * @param ruleName the RuleNameModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "ruleName/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @Override
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleNameModel ruleName, BindingResult result,
                           Model model, RedirectAttributes ra) {
        if (!result.hasErrors()) {

            ruleNameService.saveRuleName(ruleName);
            ra.addFlashAttribute("successSaveMessage", "Your rule name was successfully added");
            model.addAttribute("ruleName", ruleNameService.getAllRuleNames());

            logger.info("POST /ruleName/validate : OK");
            return "redirect:/ruleName/list";
        }
        logger.info("POST /ruleName/validate : NOK");
        return "ruleName/add";
    }

    /**
     * Render the view ruleName/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute ruleName to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "ruleName/update", returning the associated view
     * with attribute (if no Exception)
     */
    @Override
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            if (ruleNameService.checkIfIdExists(id) == false) {
                logger.info("GET /ruleName/update : Non existent id");
                return "redirect:/ruleName/list";
            }
            model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
            logger.info("GET /ruleName/update : OK");
        } catch (Exception e) {
            logger.info("/ruleName/delete : NOK " + "Invalid rule name ID " + id);
        }
        return "ruleName/update";
    }

    /**
     * Update existing rule name to the table rulename if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute ruleName to the model, containing all rule names available in DB
     *
     * @param ruleName the RuleNameModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes
     */
    @Override
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") int id,
                                 @Valid @ModelAttribute("ruleName") RuleNameModel ruleName,
                                 BindingResult result, Model model, RedirectAttributes ra) {
        if (ruleNameService.checkIfIdExists(id) == false) {
            logger.info("GET /ruleName/update : Non existent id");
            return "redirect:/ruleName/list";
        }
        if (result.hasErrors()) {
            logger.info("POST /ruleName/update : NOK");
            return "/ruleName/list";
        }
        ruleNameService.saveRuleName(ruleName);
        ra.addFlashAttribute("successUpdateMessage", "Your rule name was successfully updated");
        model.addAttribute("ruleName", ruleNameService.getAllRuleNames());

        logger.info("POST /ruleName/update : OK");
        return "redirect:/ruleName/list";
    }

    /**
     * Delete existing rule name from the table rulename
     * Add Flash Attribute with success message
     * Add attribute ruleName to the model, containing all rule names available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "ruleName/list", returning the associated view,
     * with attributes
     */
    @Override
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            if (ruleNameService.checkIfIdExists(id) == false) {
                logger.info("GET /ruleName/delete : Non existent id");
                return "redirect:/ruleName/list";
            }
            ruleNameService.deleteRuleNameById(id);
            model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
            ra.addFlashAttribute("successDeleteMessage", "This rule name was successfully deleted");

            logger.info("/ruleName/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the rule name");
            logger.info("/ruleName/delete : NOK " + "Invalid rule name ID " + id);
        }
        return "redirect:/ruleName/list";
    }
}