package com.alex.poseidon.interfaces;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;

public interface RedirectControllerInterface {
    @GetMapping("/default")
    public String defaultAfterLogin(@AuthenticationPrincipal User user);
}
