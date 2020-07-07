package com.alevel.java.nix.geronimo.controller;

import com.alevel.java.nix.geronimo.controller.graph.Graph;
import com.alevel.java.nix.geronimo.controller.graph.Node;
import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Road;
import com.alevel.java.nix.geronimo.exception.CityNotFoundException;
import com.alevel.java.nix.geronimo.exception.PlaceNotFoundException;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import com.alevel.java.nix.geronimo.service.RoadCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PlaceChooserController {

    private final CityCRUD cityCRUD;

    private final PlaceCRUD placeCRUD;

    private final RoadCRUD roadCRUD;

    @Autowired
    public PlaceChooserController(CityCRUD cityCRUD, PlaceCRUD placeCRUD, RoadCRUD roadCRUD) {
        this.cityCRUD = cityCRUD;
        this.placeCRUD = placeCRUD;
        this.roadCRUD = roadCRUD;
    }

    @GetMapping("/places-in-city/{name}")
    public String places(@PathVariable String name, Model model) {
        List<Place> places = getPlacesInCity(name);
        model.addAttribute("places", places);
        return "/place-chooser";
    }

    @PostMapping("/places-in-city/{name}")
    public String shortestWay(@PathVariable String name,
                              @RequestParam Long fromId,
                              @RequestParam Long toId,
                              Model model) {
        Optional<Place> placeFrom = placeCRUD.getById(fromId);
        Optional<Place> placeTo = placeCRUD.getById(toId);
        if(placeFrom.isEmpty()) throw new PlaceNotFoundException(fromId);
        if(placeTo.isEmpty()) throw new PlaceNotFoundException(toId);

        List<Place> places = getPlacesInCity(name);
        Graph graph = new Graph();
        Map<Place, Node> placeNodeMap = new HashMap<>();
        for (Place place : places) {
            placeNodeMap.put(place, new Node(place));
        }
        for (var entry : placeNodeMap.entrySet()) {
            List<Road> roadsFromPlace = roadCRUD.getAllFrom(entry.getValue().getPlace());
            for (Road road : roadsFromPlace) {
                Node node = placeNodeMap.get(road.getTo());
                entry.getValue().addDestination(node, road.getWeight().intValue());
                node.addDestination(entry.getValue(), road.getWeight().intValue());
            }
            graph.addNode(entry.getValue());
        }
        Graph.calculateShortestPathFromSource(placeNodeMap.get(placeFrom.get()));
        List<Node> shortestPath = placeNodeMap.get(placeTo.get()).getShortestPath();
        List<Place> shortestWay = shortestPath.stream().map(Node::getPlace).collect(Collectors.toList());
        shortestWay.add(placeTo.get());
        model.addAttribute("places", shortestWay);
        model.addAttribute("message",
                "Follow these steps in order to get there as quickly as possible.");
        return "/place-chooser";
    }

    private List<Place> getPlacesInCity(String cityName) {
        Optional<City> city = cityCRUD.getByName(cityName);
        if (city.isEmpty()) throw new CityNotFoundException(cityName);
        return placeCRUD.getAllInCity(city.get());
    }
}
