package com.db.kursach.controllers;

import com.db.kursach.models.User;
import com.db.kursach.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final UserService userService;
    public User user;

    @GetMapping("/")
    public String main_page(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }
}
