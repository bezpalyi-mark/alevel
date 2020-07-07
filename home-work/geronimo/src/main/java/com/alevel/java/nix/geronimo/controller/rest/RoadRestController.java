package com.alevel.java.nix.geronimo.controller.rest;

import com.alevel.java.nix.geronimo.entities.Road;
import com.alevel.java.nix.geronimo.entities.request.SaveRoad;
import com.alevel.java.nix.geronimo.exception.RoadNotFoundException;
import com.alevel.java.nix.geronimo.service.RoadCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roads")
public class RoadRestController {

    private final RoadCRUD roadCRUD;

    public RoadRestController(RoadCRUD roadCRUD) {
        this.roadCRUD = roadCRUD;
    }

    @GetMapping("/{id}")
    public Road get(@PathVariable Long id) {
        return roadCRUD.getById(id).orElseThrow(() -> new RoadNotFoundException(id));
    }

    @GetMapping
    public List<Road> getAll() {
        return roadCRUD.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Road create(@RequestBody SaveRoad request) {
        return roadCRUD.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SaveRoad request) {
        roadCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Road delete(@PathVariable Long id) {
        return roadCRUD.deleteById(id).orElseThrow(() -> new RoadNotFoundException(id));
    }
}
