package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;

import java.util.List;
import java.util.Optional;

public interface CityCRUD {
    List<City> getAll();

    Optional<City> getById(Long id);

    City create(SaveCity request);

    void update(Long id, SaveCity request);

    Optional<City> deleteById(Long id);

    void deleteAll();

    Optional<City> getByName(String name);
}
