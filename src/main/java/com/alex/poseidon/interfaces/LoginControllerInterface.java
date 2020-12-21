package com.alex.poseidon.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface LoginControllerInterface {
    @GetMapping("login")
    ModelAndView login();

    @GetMapping("error")
    ModelAndView error();
}
