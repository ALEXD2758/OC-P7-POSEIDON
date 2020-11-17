package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface UserControllerInterface {
    @RequestMapping("/user/list")
    String home(Model model);

    @GetMapping("/user/add")
    String addUser(User bid);

    @PostMapping("/user/validate")
    String validate(@Valid User user, BindingResult result, Model model);

    @GetMapping("/user/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/user/update/{id}")
    String updateUser(@PathVariable("id") Integer id, @Valid User user,
                      BindingResult result, Model model);

    @GetMapping("/user/delete/{id}")
    String deleteUser(@PathVariable("id") Integer id, Model model);
}
