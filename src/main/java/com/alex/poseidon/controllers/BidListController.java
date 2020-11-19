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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String validate(@Valid BidListModel bid, BindingResult result, Model model, RedirectAttributes ra) {

        if (!result.hasErrors()) {
            bidListService.saveBid(bid);
            ra.addFlashAttribute("successSaveMessage", "Your Bid was successfully added to the list");
            model.addAttribute("bidList", bidListService.getAllBids());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @Override
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("bidList", bidListService.getBidByBidListId(id));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid bidlist Id" + id);
        }
        return "bidList/update";
    }

    @Override
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid BidListModel bidList,
                            BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "/bidList/list";
        }
        bidListService.saveBid(bidList);
        ra.addFlashAttribute("successUpdateMessage", "Your bid was successfully updated");
        model.addAttribute("bidList", bidListService.getAllBids());
        return "redirect:/bidList/list";
    }

    @Override
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            bidListService.deleteBidById(id);
            model.addAttribute("bidList", bidListService.getAllBids());
            ra.addFlashAttribute("successDeleteMessage", "This bid was successfully deleted");
        } catch (Exception e) {
            ra.addFlashAttribute("errorDeleteMessage", "Error during deletion of the bid");
            throw new IllegalArgumentException("Invalid bid Id" + id);
        }
        return "redirect:/bidList/list";
    }
}
