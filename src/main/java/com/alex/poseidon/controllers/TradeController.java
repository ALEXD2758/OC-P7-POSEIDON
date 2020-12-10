package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.TradeControllerInterface;
import com.alex.poseidon.models.CurvePointModel;
import com.alex.poseidon.models.TradeModel;
import com.alex.poseidon.services.BidListService;
import com.alex.poseidon.services.TradeService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TradeController implements TradeControllerInterface {

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
    @Override
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
    @Override
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
    @Override
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
    @Override
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("trade", tradeService.getTradeById(id));
            logger.info("GET /trade/update : OK");
        } catch (Exception e) {
            logger.info("GET /trade/delete : NOK " + "Invalid trade ID " + id);
            throw new IllegalArgumentException("Invalid trade ID " + id);
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
    @Override
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") int id, @Valid @ModelAttribute("trade") TradeModel trade,
                              BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            logger.info("POST /trade/update : NOK");
            return "/trade/list";
        }
        //  trade.setCreationDate(tradeService.getTimestampForFieldCreationDate());
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
    @Override
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
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