package com.db.kursach.controllers;

import com.db.kursach.models.Employee;
import com.db.kursach.models.User;
import com.db.kursach.services.EmployeeService;
import com.db.kursach.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final UserService userService;
    private final EmployeeService employeeService;
    public User user;

    @GetMapping("/")
    public String main_page(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }

    @GetMapping("/isEmailUnique")
    public ResponseEntity<String> isEmailUnique(@RequestParam String email) {
        Employee employee = employeeService.getByEmail(email);
        return employee == null ? ResponseEntity.ok("true") : ResponseEntity.ok("false");
    }
}
