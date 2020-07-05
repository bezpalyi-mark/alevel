package com.alevel.java.nix.geronimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityChooserController {

    @GetMapping("/")
    public String mainCities() {
        return "index";
    }
}
