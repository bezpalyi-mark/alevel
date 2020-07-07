package com.alevel.java.nix.geronimo.repository;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Road;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road, Long> {
    List<Road> findByFrom(Place from);
    List<Road> deleteAllByFromOrTo(Place from, Place to);
}
