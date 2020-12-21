package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.UserModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface UserControllerInterface {
    @RequestMapping("/user/list")
    String home(Model model);

    @GetMapping("/user/add")
    String addUser(Model model);

    @PostMapping("/user/validate")
    String validate(@Valid @ModelAttribute("user") UserModel user, BindingResult result, Model model
            , RedirectAttributes ra);

    @GetMapping("/user/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/user/update/{id}")
    String updateUser(@PathVariable("id") int id, @Valid @ModelAttribute("user") UserModel user,
                      BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/user/delete/{id}")
    String deleteUser(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}