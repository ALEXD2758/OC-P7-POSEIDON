package com.alex.poseidon.controllers;

import com.alex.poseidon.models.TradeModel;
import com.alex.poseidon.services.TradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
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
public class TradeController {

    private static final Logger logger = LogManager.getLogger("TradeController");

    @Autowired
    TradeService tradeService;

    /**
     * Render the view trade/list
     * Adds attribute trade to the model, containing all trades available in DB
     *
     * @param model Model Interface, to add attributes to it
     * @return a string to the address "trade/list", returning the associated view
     * with attribute
     */
    @GetMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trade", tradeService.getAllTrades());
        logger.info("GET /trade/list : OK");
        return "trade/list";
    }

    /**
     * Render the view trade/add
     * Adds attribute trade to the model, containing a new TradeModel
     *
     * @param model for the Model Interface, to add attributes to it
     * @return a string to the address "trade/add", returning the associated view
     * with attribute
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Model model) {
        LocalDateTime dateDebut = tradeService.getCreationDateForDateFields();

        TradeModel trade = new TradeModel();
        trade.setCreationDate(dateDebut);
        model.addAttribute("trade", trade);
        logger.info("GET /trade/add : OK");
        return "trade/add";
    }

    /**
     * Save new trade to the table trade if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute trade to the model, containing all trades available in DB
     *
     * @param trade the TradeModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes if no errors in BindingResult
     * @return a string to the address "trade/add", returning the associated view,
     *  if there is an error in BindingResult
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeModel trade, BindingResult result, Model model
            , RedirectAttributes ra) {
        if (!result.hasErrors()) {
            tradeService.saveTrade(trade);
            ra.addFlashAttribute("successSaveMessage", "Your trade was successfully added");
            model.addAttribute("trade", tradeService.getAllTrades());

            logger.info("POST /trade/validate : OK");
            return "redirect:/trade/list";
        }
        logger.info("POST /trade/validate : NOK");
        return "trade/add";
    }

    /**
     * Render the view trade/update with the chosen id in a model attribute
     * with the associated data of the chosen ID
     * Add attribute trade to the model
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @return a string to the address "trade/update", returning the associated view
     * with attribute (if no Exception)
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            if (tradeService.checkIfTradeIdExists(id) == false) {
                logger.info("GET /trade/update : Non existent id");
                return "redirect:/trade/list";
            }
            model.addAttribute("trade", tradeService.getTradeById(id));
            logger.info("GET /trade/update : OK");
        } catch (Exception e) {
            logger.info("GET /trade/delete : NOK " + "Invalid trade ID " + id);
        }
        return "trade/update";
    }

    /**
     * Update existing trade to the table trade if BindingResult has no errors
     * Add Flash Attribute with success message
     * Add attribute trade to the model, containing all trades available in DB
     *
     * @param trade the TradeModel with annotation @Valid (for the possible constraints)
     * @param result to represent binding results
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") int id, @Valid @ModelAttribute("trade") TradeModel trade,
                              BindingResult result, Model model, RedirectAttributes ra) {
        if (tradeService.checkIfTradeIdExists(id) == false) {
            logger.info("POST /trade/update : Non existent id");
            return "redirect:/trade/list";
        }
        if (result.hasErrors()) {
            logger.info("POST /trade/update : NOK");
            return "/trade/list";
        }
        tradeService.saveTrade(trade);
        ra.addFlashAttribute("successUpdateMessage", "Your trade was successfully updated");
        model.addAttribute("trade", tradeService.getAllTrades());

        logger.info("POST /trade/update : OK");
        return "redirect:/trade/list";
    }

    /**
     * Delete existing trade from the table trade
     * Add Flash Attribute with success message
     * Add attribute trade to the model, containing all trades available in DB
     *
     * @param id the int of the ID chosen by the user
     * @param model the Model Interface, to add attributes to it
     * @param ra the RedirectAttributes to redirect attributes in redirect
     * @return a string to the address "trade/list", returning the associated view,
     * with attributes
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            if (tradeService.checkIfTradeIdExists(id) == false) {
                logger.info("GET /trade/delete : Non existent id");
                return "redirect:/trade/list";
            }
            tradeService.deleteTradeById(id);
            model.addAttribute("trade", tradeService.getAllTrades());
            ra.addFlashAttribute("successDeleteMessage", "This trade was successfully deleted");

            logger.info("/trade/delete : OK");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the trade");
            logger.info("/trade/delete : NOK " + "Invalid trade ID " + id);
        }
        return "redirect:/trade/list";
    }
}