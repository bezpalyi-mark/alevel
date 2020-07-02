package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;
import com.alevel.java.nix.geronimo.exception.CityNotFoundException;
import com.alevel.java.nix.geronimo.repository.CityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService implements CityCRUD {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City create(SaveCity request) {
        City city = new City();
        city.setName(request.getName());
        cityRepository.save(city);
        return city;
    }

    @Override
    public void update(Long id, SaveCity request) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
        city.setName(request.getName());
        cityRepository.save(city);
    }

    @Override
    public Optional<City> deleteById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        city.ifPresent(cityRepository::delete);
        return city;
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }

    @Override
    public Optional<City> getByName(String name) {
        return cityRepository.findByName(name);
    }
}
