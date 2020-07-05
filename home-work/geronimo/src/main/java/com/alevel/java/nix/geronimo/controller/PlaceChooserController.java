package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.exception.CityNotFoundException;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PlaceChooserController {

    private final CityCRUD cityCRUD;

    private final PlaceCRUD placeCRUD;

    @Autowired
    public PlaceChooserController(CityCRUD cityCRUD, PlaceCRUD placeCRUD) {
        this.cityCRUD = cityCRUD;
        this.placeCRUD = placeCRUD;
    }

    @GetMapping("/places-in-city/{name}")
    public String main(@PathVariable String name, Model model) {
        System.out.println("Hello");
        Optional<City> city = cityCRUD.getByName(name);
        if (city.isEmpty()) throw new CityNotFoundException(name);
        List<Place> places = placeCRUD.getAllInCity(city.get());
        model.addAttribute("places", places);
        return "/place-chooser";
    }
}
