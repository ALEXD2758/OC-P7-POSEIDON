package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.CurvePointModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface CurveControllerInterface {
    @RequestMapping("/curvePoint/list")
    String home(Model model);

    @GetMapping("/curvePoint/add")
    String addCurvePointForm(Model model);

    @PostMapping("/curvePoint/validate")
    String validate(@Valid CurvePointModel curvePoint, BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/curvePoint/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/curvePoint/update/{id}")
    String updateCurvePoint(@PathVariable("id") int id, @Valid CurvePointModel curvePoint,
                     BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/curvePoint/delete/{id}")
    String deleteCurvePoint(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}
