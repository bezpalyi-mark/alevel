package com.alevel.java.nix.geronimo;

import com.alevel.java.nix.geronimo.entities.Place;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;
import com.alevel.java.nix.geronimo.entities.request.SavePlace;
import com.alevel.java.nix.geronimo.service.CategoryCRUD;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import com.alevel.java.nix.geronimo.service.PlaceCRUD;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GeronimoPlaceEntityTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private CityCRUD cityCRUD;

    @Autowired
    private CategoryCRUD categoryCRUD;

    @Autowired
    private PlaceCRUD placeCRUD;

    @BeforeEach
    void setUp() {
        var cityRequest = new SaveCity();
        cityRequest.setName("test city");
        cityCRUD.create(cityRequest);

        cityRequest = new SaveCity();
        cityRequest.setName("second test city");
        cityCRUD.create(cityRequest);

        var categoryRequest = new SaveCategory();
        categoryRequest.setName("entertainment");
        categoryCRUD.create(categoryRequest);

        categoryRequest = new SaveCategory();
        categoryRequest.setName("job");
        categoryCRUD.create(categoryRequest);
    }

    @AfterEach
    void end() {
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
    void testCreatePlace() {
        String name = "test place";
        String rating = "good";
        String category = "entertainment";
        String city = "test city";
        ResponseEntity<Place> placeResponseEntity = createPlace(name, rating, false, category, city);

        assertEquals(HttpStatus.CREATED, placeResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, placeResponseEntity.getHeaders().getContentType());

        Place responseBody = placeResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertEquals(rating.toUpperCase(), responseBody.getRating().name());
        assertFalse(responseBody.isCrossroads());
        assertEquals(category, responseBody.getCategory().getName());
        assertEquals(city, responseBody.getCity().getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetPlaceById() {
        String name = "test get place";
        String rating = "terribly";
        String category = "entertainment";
        String city = "test city";

        var place = createPlace(name, rating, false, category, city).getBody();
        assertNotNull(place);

        Long id = place.getId();

        var placeUrl = "http://localhost:" + port + "/places/" + id;

        ResponseEntity<Place> placeResponseEntity = rest.getForEntity(placeUrl, Place.class);
        assertEquals(HttpStatus.OK, placeResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, placeResponseEntity.getHeaders().getContentType());

        Place responseBody = placeResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertEquals(rating.toUpperCase(), responseBody.getRating().name());
        assertEquals(category, responseBody.getCategory().getName());
        assertEquals(city, responseBody.getCity().getName());
        assertEquals(id, responseBody.getId());
        assertFalse(responseBody.isCrossroads());
    }

    @Test
    void testGetNonExistingPlace() {
        final int nonExistingId = 999;
        var placeUrl = "http://localhost:" + port + "/places/" + nonExistingId;

        ResponseEntity<Place> placeResponseEntity = rest.getForEntity(placeUrl, Place.class);

        assertEquals(HttpStatus.NOT_FOUND, placeResponseEntity.getStatusCode());
    }

    @Test
    void testUpdatePlace() {
        String name = "test place";
        String rating = "good";
        String category = "entertainment";
        String city = "test city";

        var place = createPlace(name, rating, true, category, city).getBody();
        assertNotNull(place);

        Long id = place.getId();

        var placeUrl = "http://localhost:" + port + "/places/" + id;

        String updatedName = "test place updated";
        String updatedRating = "excellent";
        String updatedCategory = "job";
        String updatedCity = "second test city";

        var request = new SavePlace(updatedName, updatedCategory, updatedRating, updatedCity, false);
        rest.put(placeUrl, request);

        ResponseEntity<Place> placeResponseEntity = rest.getForEntity(placeUrl, Place.class);
        assertEquals(HttpStatus.OK, placeResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, placeResponseEntity.getHeaders().getContentType());

        Place responseBody = placeResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(updatedName, responseBody.getName());
        assertEquals(updatedRating.toUpperCase(), responseBody.getRating().name());
        assertEquals(updatedCategory, responseBody.getCategory().getName());
        assertEquals(updatedCity, responseBody.getCity().getName());
        assertEquals(id, responseBody.getId());
        assertFalse(responseBody.isCrossroads());
    }

    @Test
    void testDeletePlace() {
        String name = "test place";
        String rating = "good";
        String category = "entertainment";
        String city = "test city";

        var place = createPlace(name, rating, false, category, city).getBody();
        assertNotNull(place);

        Long id = place.getId();

        var placeUrl = "http://localhost:" + port + "/places/" + id;
        var placeUri = URI.create(placeUrl);

        ResponseEntity<Place> placeResponseEntity = rest
                .exchange(RequestEntity.delete(placeUri).build(), Place.class);

        assertEquals(HttpStatus.OK, placeResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, placeResponseEntity.getHeaders().getContentType());

        Place responseBody = placeResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertEquals(rating.toUpperCase(), responseBody.getRating().name());
        assertEquals(category, responseBody.getCategory().getName());
        assertEquals(city, responseBody.getCity().getName());
        assertEquals(id, responseBody.getId());
        assertFalse(responseBody.isCrossroads());

        assertEquals(HttpStatus.NOT_FOUND, rest
                .getForEntity(placeUrl, Place.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(placeUri).build(), Place.class)
                .getStatusCode());
    }

    private ResponseEntity<Place> createPlace(String name, String rating, boolean isCrossroads,
                                              String category, String city) {
        String url = "http://localhost:" + port + "/places";
        var requestBody = new SavePlace();
        requestBody.setName(name);
        requestBody.setCrossroads(isCrossroads);
        requestBody.setRatingName(rating);
        requestBody.setCategoryName(category);
        requestBody.setCityName(city);

        return rest.postForEntity(url, requestBody, Place.class);
    }
}