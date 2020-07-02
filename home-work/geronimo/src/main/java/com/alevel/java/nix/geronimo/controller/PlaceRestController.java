package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.request.SavePlace;
import com.alevel.java.nix.geronimo.exception.PlaceNotFoundException;
import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceRestController {

    private final PlaceCRUD placeCRUD;

    public PlaceRestController(PlaceCRUD placeCRUD) {
        this.placeCRUD = placeCRUD;
    }

    @GetMapping("/{id}")
    public Place get(@PathVariable Long id) {
        return placeCRUD.getById(id).orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @GetMapping
    public List<Place> getAll() {
        return placeCRUD.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Place create(@RequestBody SavePlace request) {
        return placeCRUD.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SavePlace request) {
        placeCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Place delete(@PathVariable Long id) {
        return placeCRUD.deleteById(id).orElseThrow(() -> new PlaceNotFoundException(id));
    }
}
