package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.Rating;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface RatingControllerInterface {
    @RequestMapping("/rating/list")
    String home(Model model);

    @GetMapping("/rating/add")
    String addRatingForm(Rating rating);

    @PostMapping("/rating/validate")
    String validate(@Valid Rating rating, BindingResult result, Model model);

    @GetMapping("/rating/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/rating/update/{id}")
    String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                        BindingResult result, Model model);

    @GetMapping("/rating/delete/{id}")
    String deleteRating(@PathVariable("id") Integer id, Model model);
}
