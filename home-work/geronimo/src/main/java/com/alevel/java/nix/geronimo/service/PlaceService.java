package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Category;
import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Rating;
import com.alevel.java.nix.geronimo.entities.request.SavePlace;
import com.alevel.java.nix.geronimo.exception.CategoryNotFoundException;
import com.alevel.java.nix.geronimo.exception.CityNotFoundException;
import com.alevel.java.nix.geronimo.exception.NoSuchRatingException;
import com.alevel.java.nix.geronimo.exception.PlaceNotFoundException;
import com.alevel.java.nix.geronimo.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlaceService implements PlaceCRUD {

    private final PlaceRepository placeRepository;

    private final CategoryCRUD categoryCRUD;

    private final CityCRUD cityCRUD;

    public PlaceService(PlaceRepository placeRepository, CategoryCRUD categoryCRUD, CityCRUD cityCRUD) {
        this.placeRepository = placeRepository;
        this.categoryCRUD = categoryCRUD;
        this.cityCRUD = cityCRUD;
    }

    @Override
    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> getById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Place create(SavePlace request) {
        Place place = new Place();
        place.setName(request.getName());
        place.setCrossroads(request.isCrossroads());
        place.setCategory(getCategory(request.getCategoryName()));
        place.setRating(getRating(request.getRatingName()));
        place.setCity(cityCRUD.getByName(request.getCityName()).orElseThrow(() -> new CityNotFoundException(request.getCityName())));
        placeRepository.save(place);
        return place;
    }

    @Override
    public void update(Long id, SavePlace request) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new PlaceNotFoundException(id));
        place.setName(request.getName());
        place.setRating(getRating(request.getRatingName()));
        place.setCategory(getCategory(request.getCategoryName()));
        place.setCrossroads(request.isCrossroads());
        place.setCity(cityCRUD.getByName(request.getCityName()).orElseThrow(() -> new CityNotFoundException(request.getCityName())));
        placeRepository.save(place);
    }

    @Override
    public Optional<Place> deleteById(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        place.ifPresent(placeRepository::delete);
        return place;
    }

    @Override
    public void deleteAll() {
        placeRepository.deleteAll();
    }

    @Override
    public Optional<Place> getByName(String name) {
        return placeRepository.findByName(name);
    }

    private Rating getRating(String ratingName) {
        try {
            return Rating.valueOf(ratingName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoSuchRatingException(ratingName);
        }
    }

    private Category getCategory(String categoryName) {
        Optional<Category> category = categoryCRUD.getByName(categoryName);
        if(category.isEmpty()) {
            throw new CategoryNotFoundException(categoryName);
        }
        return category.get();
    }
}
