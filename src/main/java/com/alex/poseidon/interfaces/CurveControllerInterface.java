package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.CurvePoint;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface CurveControllerInterface {
    @RequestMapping("/curvePoint/list")
    String home(Model model);

    @GetMapping("/curvePoint/add")
    String addBidForm(CurvePoint bid);

    @PostMapping("/curvePoint/validate")
    String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model);

    @GetMapping("/curvePoint/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/curvePoint/update/{id}")
    String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                     BindingResult result, Model model);

    @GetMapping("/curvePoint/delete/{id}")
    String deleteBid(@PathVariable("id") Integer id, Model model);
}
