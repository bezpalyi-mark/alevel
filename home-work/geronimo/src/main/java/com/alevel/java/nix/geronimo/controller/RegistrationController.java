package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.entities.Role;
import com.alevel.java.nix.geronimo.entities.User;
import com.alevel.java.nix.geronimo.entities.request.SaveUser;
import com.alevel.java.nix.geronimo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(required = false) String username,
                          @RequestParam(required = false) String password,
                          Model model) {
        if(username == null || password == null) {
            return "registration";
        }
        SaveUser request = new SaveUser();
        request.setUsername(username);
        request.setPassword(password);
        User userFromDb = userService.findByUsername(request.getUsername());

        if(userFromDb != null) {
            model.addAttribute("username", userFromDb.getUsername());
            return "registration";
        }
        request.setActive(true);
        request.setRoles(Collections.singleton(Role.USER));
        userService.create(request);
        return "redirect:/login";
    }
}
