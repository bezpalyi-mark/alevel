package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.request.SavePlace;

import java.util.List;
import java.util.Optional;

public interface PlaceCRUD {
    List<Place> getAll();

    Optional<Place> getById(Long id);

    Place create(SavePlace request);

    void update(Long id, SavePlace request);

    Optional<Place> deleteById(Long id);

    void deleteAll();

    Optional<Place> getByName(String name);
}
