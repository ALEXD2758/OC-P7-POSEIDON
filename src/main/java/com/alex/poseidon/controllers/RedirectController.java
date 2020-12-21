package com.alex.poseidon.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    /**
     * Check if the authenticated user has ADMIN authority/role
     *
     * @param user model core User retrieved by UserDetailsService
     * @return a string to the address "user/list" if true, trade/list if false
     */
    @GetMapping("/default")
    public String defaultAfterLogin(@AuthenticationPrincipal User user) {
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")) == true) {
            return "redirect:/user/list";
        }
        return "redirect:/trade/list";
    }
}