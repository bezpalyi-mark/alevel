package com.alevel.java.nix.todolist.service;

import com.alevel.java.nix.todolist.entity.Item;
import com.alevel.java.nix.todolist.entity.request.SaveItem;

import java.util.List;
import java.util.Optional;

public interface ItemsCRUD {
    List<Item> getAll();

    Optional<Item> getById(Long id);

    List<Item> getAllUndone();

    Item create(SaveItem request);

    void update(Long id, SaveItem request);

    Optional<Item> deleteById(Long id);
}
