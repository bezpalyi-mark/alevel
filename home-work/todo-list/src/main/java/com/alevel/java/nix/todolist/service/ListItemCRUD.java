package com.alevel.java.nix.todolist.service;

import com.alevel.java.nix.todolist.entity.ListItem;
import com.alevel.java.nix.todolist.entity.request.SaveListItem;

import java.util.List;
import java.util.Optional;

public interface ListItemCRUD {
    List<ListItem> getAll();

    Optional<ListItem> getById(Long id);

    List<ListItem> getAllUndone();

    ListItem create(SaveListItem request);

    void update(Long id, SaveListItem request);

    Optional<ListItem> deleteById(Long id);
}
