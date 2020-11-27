package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.RatingModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface RatingControllerInterface {
    @RequestMapping("/rating/list")
    String home(Model model);

    @GetMapping("/rating/add")
    String addRatingForm(Model model);

    @PostMapping("/rating/validate")
    String validate(@Valid @ModelAttribute("rating") RatingModel rating, BindingResult result, Model model,
                    RedirectAttributes ra);

    @GetMapping("/rating/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/rating/update/{id}")
    String updateRating(@PathVariable("id") int id,
                        @Valid @ModelAttribute("rating") RatingModel rating,
                        BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/rating/delete/{id}")
    String deleteRating(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}
