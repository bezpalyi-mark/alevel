package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/control-panel")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {

    private final PlaceCRUD placeCRUD;

    @Autowired
    public AdminPanelController(PlaceCRUD placeCRUD) {
        this.placeCRUD = placeCRUD;
    }

    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("places", placeCRUD.getAll());
        return "control-panel";
    }
}
