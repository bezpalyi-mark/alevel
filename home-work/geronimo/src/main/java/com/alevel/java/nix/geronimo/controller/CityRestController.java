package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;
import com.alevel.java.nix.geronimo.exception.CityNotFoundException;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityRestController {

    private final CityCRUD cityCRUD;

    public CityRestController(CityCRUD cityCRUD) {
        this.cityCRUD = cityCRUD;
    }

    @GetMapping("/{id}")
    public City get(@PathVariable Long id) {
        return cityCRUD.getById(id).orElseThrow(() -> new CityNotFoundException(id));
    }

    @GetMapping
    public List<City> getAll() {
        return cityCRUD.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody SaveCity request) {
        return cityCRUD.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SaveCity request) {
        cityCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public City delete(@PathVariable Long id) {
        return cityCRUD.deleteById(id).orElseThrow(() -> new CityNotFoundException(id));
    }
}
