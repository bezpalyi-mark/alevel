package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Road;
import com.alevel.java.nix.geronimo.entities.request.SaveRoad;

import java.util.List;
import java.util.Optional;

public interface RoadCRUD {
    List<Road> getAll();

    Optional<Road> getById(Long id);

    Road create(SaveRoad request);

    void update(Long id, SaveRoad request);

    Optional<Road> deleteById(Long id);

    void deleteAll();

    List<Road> getAllFrom(Place from);
}
