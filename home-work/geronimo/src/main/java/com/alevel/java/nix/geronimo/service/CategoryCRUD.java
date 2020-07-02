package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Category;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryCRUD {
    List<Category> getAll();

    Optional<Category> getById(Long id);

    Optional<Category> getByName(String name);

    Category create(SaveCategory request);

    void update(Long id, SaveCategory request);

    Optional<Category> deleteById(Long id);

    void deleteAll();
}
