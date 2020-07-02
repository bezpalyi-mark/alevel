package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.entities.Category;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;
import com.alevel.java.nix.geronimo.exception.CategoryNotFoundException;
import com.alevel.java.nix.geronimo.service.CategoryCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryCRUD categoryCRUD;

    public CategoryRestController(CategoryCRUD categoryCRUD) {
        this.categoryCRUD = categoryCRUD;
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) {
        return categoryCRUD.getById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryCRUD.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody SaveCategory request) {
        return categoryCRUD.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SaveCategory request) {
        categoryCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Category delete(@PathVariable Long id) {
        return categoryCRUD.deleteById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
