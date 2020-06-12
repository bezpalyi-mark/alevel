package com.alevel.java.nix.todolist.controller;

import com.alevel.java.nix.todolist.entity.ListItem;
import com.alevel.java.nix.todolist.entity.request.SaveListItem;
import com.alevel.java.nix.todolist.exception.ListItemNotFoundException;
import com.alevel.java.nix.todolist.service.ListItemCRUD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-list")
public class ListItemController {

    private final ListItemCRUD listItemCRUD;

    public ListItemController(ListItemCRUD listItemCRUD) {
        this.listItemCRUD = listItemCRUD;
    }

    @GetMapping("/{id}")
    public ListItem get(@PathVariable Long id) {
        return listItemCRUD.getById(id).orElseThrow(() -> new ListItemNotFoundException(id));
    }

    @GetMapping("/all")
    public List<ListItem> getAll() {
        return listItemCRUD.getAll();
    }

    @GetMapping("/all-undone")
    public List<ListItem> getAllUndone() {
        return listItemCRUD.getAllUndone();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ListItem create(@RequestBody SaveListItem request) {
        return listItemCRUD.create(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SaveListItem request) {
        listItemCRUD.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ListItem delete(@PathVariable Long id) {
        return listItemCRUD.deleteById(id).orElseThrow(() -> new ListItemNotFoundException(id));
    }
}
