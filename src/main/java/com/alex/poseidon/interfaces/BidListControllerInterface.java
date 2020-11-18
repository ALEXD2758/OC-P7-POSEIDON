package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.BidListModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface BidListControllerInterface {
    @RequestMapping("/bidList/list")
    String home(Model model);

    @GetMapping("/bidList/add")
    String addBidForm(Model model);

    @PostMapping("/bidList/validate")
    String validate(@Valid BidListModel bid, BindingResult result, Model model);

    @GetMapping("/bidList/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/bidList/update/{id}")
    String updateBid(@PathVariable("id") Integer id, @Valid BidListModel bidList,
                     BindingResult result, Model model);

    @GetMapping("/bidList/delete/{id}")
    String deleteBid(@PathVariable("id") Integer id, Model model);
}
