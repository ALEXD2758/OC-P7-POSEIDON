package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.BidListModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface BidListControllerInterface {
    @RequestMapping("/bidList/list")
    String home(Model model);

    @GetMapping("/bidList/add")
    String addBidForm(Model model);

    @PostMapping("/bidList/validate")
    String validate(@Valid BidListModel bid, BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/bidList/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/bidList/update/{id}")
    String updateBid(@PathVariable("id") int id, @Valid BidListModel bidList,
                     BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/bidList/delete/{id}")
    String deleteBid(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}
