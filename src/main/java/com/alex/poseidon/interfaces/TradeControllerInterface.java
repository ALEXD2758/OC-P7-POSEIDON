package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.TradeModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface TradeControllerInterface {
    @GetMapping("/trade/list")
    String home(Model model);

    @GetMapping("/trade/add")
    String addTradeForm(Model model);

    @PostMapping("/trade/validate")
    String validate(@Valid @ModelAttribute("trade") TradeModel trade, BindingResult result, Model model
            , RedirectAttributes ra);

    @GetMapping("/trade/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/trade/update/{id}")
    String updateTrade(@PathVariable("id") int id, @Valid @ModelAttribute("trade") TradeModel trade,
                       BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/trade/delete/{id}")
    String deleteTrade(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}