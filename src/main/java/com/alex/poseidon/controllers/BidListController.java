package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.BidListControllerInterface;
import com.alex.poseidon.models.BidListModel;
import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.repositories.BidListRepository;
import com.alex.poseidon.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class BidListController implements BidListControllerInterface {

    @Autowired
    BidListService bidListService;

    @Override
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidList", bidListService.getAllBids());
        return "bidList/list";
    }

    @Override
    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidListModel());
        return "bidList/add";
    }

    @Override
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListModel bid, BindingResult result, Model model) {

        if (!result.hasErrors()) {
            bidListService.saveBid(bid);
            model.addAttribute("successMessage", "Your Bid was successfully added to the list");
            model.addAttribute("bidList", bidListService.getAllBids());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @Override
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            BidListModel bidList = bidListService.getBidByBidListId(id);
            model.addAttribute("bidList", bidList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid bidlist Id" + id);
        }
        return "bidList/update";
    }

    @Override
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid BidListModel bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/bidList/list";
        }
        bidListService.saveBid(bidList);
        model.addAttribute("bidList", bidListService.getAllBids());
        return "redirect:/bidList/list";
    }

    @Override
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") int id, Model model) {
        try {
            bidListService.deleteBidById(id);
            model.addAttribute("bidList", bidListService.getAllBids());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid bidlist Id" + id);
        }
        return "redirect:/bidList/list";
    }
}
