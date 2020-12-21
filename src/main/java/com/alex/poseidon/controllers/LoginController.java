package com.alex.poseidon.controllers;

import com.alex.poseidon.interfaces.LoginControllerInterface;
import com.alex.poseidon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController implements LoginControllerInterface {

    @Autowired
    private UserRepository userRepository;

    /**
     * Render the ModelAndView login
     *
     * @return a model and view "login"
     */
    @Override
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * Render the ModelAndView 403
     * Add an Object attribute with an error message
     *
     * @return a model and view "403"
     */
    @Override
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}