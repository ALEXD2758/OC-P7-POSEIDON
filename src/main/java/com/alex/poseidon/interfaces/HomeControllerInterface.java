package com.alex.poseidon.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public interface HomeControllerInterface {
    @RequestMapping("/")
    String home(Model model);

    @RequestMapping("/admin/home")
    String adminHome(Model model);
}
