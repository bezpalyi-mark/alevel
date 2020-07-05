package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Road;
import com.alevel.java.nix.geronimo.entities.request.SaveRoad;
import com.alevel.java.nix.geronimo.exception.PlaceNotFoundException;
import com.alevel.java.nix.geronimo.exception.RoadNotFoundException;
import com.alevel.java.nix.geronimo.repository.RoadRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoadService implements RoadCRUD {

    private final RoadRepository roadRepository;

    private final PlaceService placeService;

    public RoadService(RoadRepository roadRepository, PlaceService placeService) {
        this.roadRepository = roadRepository;
        this.placeService = placeService;
    }

    @Override
    public List<Road> getAll() {
        return roadRepository.findAll();
    }

    @Override
    public Optional<Road> getById(Long id) {
        return roadRepository.findById(id);
    }

    @Override
    public Road create(SaveRoad request) {
        Road road = new Road();
        setPlaces(road, request);
        roadRepository.save(road);
        return road;
    }

    @Override
    public void update(Long id, SaveRoad request) {
        Road road = roadRepository.findById(id).orElseThrow(() -> new RoadNotFoundException(id));
        setPlaces(road, request);
        roadRepository.save(road);
    }

    @Override
    public Optional<Road> deleteById(Long id) {
        Optional<Road> road = roadRepository.findById(id);
        road.ifPresent(roadRepository::delete);
        return road;
    }

    @Override
    public void deleteAll() {
        roadRepository.deleteAll();
    }

    @Override
    public List<Road> getAllFrom(Place from) {
        return roadRepository.findByFrom(from);
    }

    private void setPlaces(Road road,SaveRoad request) {
        Optional<Place> placeFrom = placeService.getById(request.getFromId());
        Optional<Place> placeTo = placeService.getById(request.getToId());
        if(placeFrom.isEmpty()) {
            throw new PlaceNotFoundException(request.getFromId());
        }
        if(placeTo.isEmpty()) {
            throw new PlaceNotFoundException(request.getToId());
        }
        road.setFrom(placeFrom.get());
        road.setTo(placeTo.get());
    }
}
