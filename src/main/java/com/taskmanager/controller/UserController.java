package com.taskmanager.controller;

import com.taskmanager.config.UserService;
import com.taskmanager.dto.CreateUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getCreateUser(Model model) {
        model.addAttribute("user", new CreateUserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String postCreateUser(@ModelAttribute("user") @Valid CreateUserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
