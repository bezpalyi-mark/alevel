package com.alevel.java.nix.todolist.controller;

import com.alevel.java.nix.todolist.entity.Item;
import com.alevel.java.nix.todolist.entity.request.SaveItem;
import com.alevel.java.nix.todolist.exception.ItemNotFoundException;
import com.alevel.java.nix.todolist.service.ItemsCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ItemsController {

    private final ItemsCRUD itemsCRUD;

    public ItemsController(ItemsCRUD itemsCRUD) {
        this.itemsCRUD = itemsCRUD;
    }

    @GetMapping("/{id}")
    public Item get(@PathVariable Long id) {
        return itemsCRUD.getById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping
    public List<Item> getAll(@RequestParam(required = false, defaultValue = "true") String done) {
        if(done.equals("false")) return itemsCRUD.getAllUndone();
        return itemsCRUD.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Item create(@RequestBody SaveItem request) {
        return itemsCRUD.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SaveItem request) {
        itemsCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Item delete(@PathVariable Long id) {
        return itemsCRUD.deleteById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
