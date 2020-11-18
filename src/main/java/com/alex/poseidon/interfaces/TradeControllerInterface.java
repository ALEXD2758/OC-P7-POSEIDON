package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.TradeModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface TradeControllerInterface {
    @RequestMapping("/trade/list")
    String home(Model model);

    @GetMapping("/trade/add")
    String addUser(TradeModel bid);

    @PostMapping("/trade/validate")
    String validate(@Valid TradeModel trade, BindingResult result, Model model);

    @GetMapping("/trade/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/trade/update/{id}")
    String updateTrade(@PathVariable("id") Integer id, @Valid TradeModel trade,
                       BindingResult result, Model model);

    @GetMapping("/trade/delete/{id}")
    String deleteTrade(@PathVariable("id") Integer id, Model model);
}
