package com.alevel.java.nix.geronimo.repository;

import com.alevel.java.nix.geronimo.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
}
