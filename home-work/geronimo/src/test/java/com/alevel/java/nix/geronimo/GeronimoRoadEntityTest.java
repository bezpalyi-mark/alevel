package com.alevel.java.nix.geronimo;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.Road;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;
import com.alevel.java.nix.geronimo.entities.request.SavePlace;
import com.alevel.java.nix.geronimo.entities.request.SaveRoad;
import com.alevel.java.nix.geronimo.exception.PlaceNotFoundException;
import com.alevel.java.nix.geronimo.service.CategoryCRUD;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import com.alevel.java.nix.geronimo.service.RoadCRUD;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeronimoRoadEntityTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeronimoRoadEntityTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private PlaceCRUD placeCRUD;

    @Autowired
    private CityCRUD cityCRUD;

    @Autowired
    private CategoryCRUD categoryCRUD;

    @Autowired
    private RoadCRUD roadCRUD;

    @BeforeEach
    void setUp() {
        var cityRequest = new SaveCity();
        cityRequest.setName("test city");
        cityCRUD.create(cityRequest);

        var categoryRequest = new SaveCategory();
        categoryRequest.setName("entertainment");
        categoryCRUD.create(categoryRequest);

        categoryRequest = new SaveCategory();
        categoryRequest.setName("job");
        categoryCRUD.create(categoryRequest);

        String name = "test place";
        String rating = "good";
        String category = "entertainment";
        String city = "test city";
        var placeRequest = new SavePlace(name, category, rating, city, true);
        placeCRUD.create(placeRequest);

        String secondName = "second test place";
        String secondRating = "excellent";
        String secondCategory = "job";
        placeRequest = new SavePlace(secondName, secondCategory, secondRating, city, false);
        placeCRUD.create(placeRequest);

        String thirdName = "third test place";
        String thirdRating = "SATISFACTORILY";
        String thirdCategory = "job";
        placeRequest = new SavePlace(thirdName, thirdCategory, thirdRating, city, false);
        placeCRUD.create(placeRequest);

        String fourthName = "fourth test place";
        String fourthRating = "terribly";
        String fourthCategory = "entertainment";
        placeRequest = new SavePlace(fourthName, fourthCategory, fourthRating, city, true);
        placeCRUD.create(placeRequest);
    }

    @AfterEach
    void end() {
        roadCRUD.deleteAll();
        placeCRUD.deleteAll();
        cityCRUD.deleteAll();
        categoryCRUD.deleteAll();
    }

    @Test
    void testContextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    void testCreateRoad() {
        String placeName = "test place";
        String secondPlaceName = "second test place";
        ResponseEntity<Road> roadResponseEntity = createRoad(placeName, secondPlaceName);

        assertEquals(HttpStatus.CREATED, roadResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, roadResponseEntity.getHeaders().getContentType());

        Road responseBody = roadResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(placeName, responseBody.getFrom().getName());
        assertEquals(secondPlaceName, responseBody.getTo().getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetById() {
        String placeName = "test place";
        String secondPlaceName = "second test place";
        var road = createRoad(placeName, secondPlaceName).getBody();
        assertNotNull(road);

        Long id = road.getId();

        var roadUrl = "http://localhost:" + port + "/roads/" + id;

        ResponseEntity<Road> roadResponseEntity = rest.getForEntity(roadUrl, Road.class);
        assertEquals(HttpStatus.OK, roadResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, roadResponseEntity.getHeaders().getContentType());

        Road responseBody = roadResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(placeName, responseBody.getFrom().getName());
        assertEquals(secondPlaceName, responseBody.getTo().getName());
        assertNotNull(responseBody.getId());
        assertEquals(id, responseBody.getId());
    }

    @Test
    void testGetNonExistingRoad() {
        final int nonExistingId = 999;
        var roadUrl = "http://localhost:" + port + "/places/" + nonExistingId;
        ResponseEntity<Road> roadResponseEntity = rest.getForEntity(roadUrl, Road.class);
        assertEquals(HttpStatus.NOT_FOUND, roadResponseEntity.getStatusCode());
    }

    @Test
    void testUpdateRoad() {
        String placeName = "test place";
        String secondPlaceName = "second test place";
        var road = createRoad(placeName, secondPlaceName).getBody();
        assertNotNull(road);

        Long id = road.getId();

        var roadUrl = "http://localhost:" + port + "/roads/" + id;

        String updatedPlaceName = "third test place";

        var request = new SaveRoad();
        Optional<Place> optionalThirdPlace = placeCRUD.getByName(updatedPlaceName);
        if (optionalThirdPlace.isEmpty()) {
            LOGGER.error("Cannot get first test city");
            throw new PlaceNotFoundException(updatedPlaceName);
        }
        request.setFromId(road.getFrom().getId());
        request.setToId(optionalThirdPlace.get().getId());
        rest.put(roadUrl, request);

        ResponseEntity<Road> roadResponseEntity = rest.getForEntity(roadUrl, Road.class);
        assertEquals(HttpStatus.OK, roadResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, roadResponseEntity.getHeaders().getContentType());

        Road responseBody = roadResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(placeName, responseBody.getFrom().getName());
        assertEquals(updatedPlaceName, responseBody.getTo().getName());
        assertEquals(id, responseBody.getId());
    }

    @Test
    void testDeletePlace() {
        String placeName = "third test place";
        String secondPlaceName = "fourth test place";

        var road = createRoad(placeName, secondPlaceName).getBody();
        assertNotNull(road);

        Long id = road.getId();

        var roadUrl = "http://localhost:" + port + "/roads/" + id;
        var roadUri = URI.create(roadUrl);

        ResponseEntity<Road> roadResponseEntity = rest
                .exchange(RequestEntity.delete(roadUri).build(), Road.class);

        assertEquals(HttpStatus.OK, roadResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, roadResponseEntity.getHeaders().getContentType());

        Road responseBody = roadResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(placeName, responseBody.getFrom().getName());
        assertEquals(secondPlaceName, responseBody.getTo().getName());
        assertNotNull(responseBody.getId());
        assertEquals(id, responseBody.getId());

        assertEquals(HttpStatus.NOT_FOUND, rest
                .getForEntity(roadUrl, Road.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(roadUri).build(), Road.class)
                .getStatusCode());
    }

    private ResponseEntity<Road> createRoad(String placeFrom, String placeTo) {
        String url = "http://localhost:" + port + "/roads";
        var requestBody = new SaveRoad();
        Optional<Place> optionalPlaceFrom = placeCRUD.getByName(placeFrom);
        Optional<Place> optionalPlaceTo = placeCRUD.getByName(placeTo);
        if (optionalPlaceFrom.isEmpty()) {
            LOGGER.error("Cannot get first test city");
            throw new PlaceNotFoundException(placeFrom);
        }
        if (optionalPlaceTo.isEmpty()) {
            LOGGER.error("Cannot get second test city");
            throw new PlaceNotFoundException(placeFrom);
        }
        requestBody.setFromId(optionalPlaceFrom.get().getId());
        requestBody.setToId(optionalPlaceTo.get().getId());

        return rest.postForEntity(url, requestBody, Road.class);
    }
}
