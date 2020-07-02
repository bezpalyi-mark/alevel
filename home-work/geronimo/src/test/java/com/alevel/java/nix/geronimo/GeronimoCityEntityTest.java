package com.alevel.java.nix.geronimo;

import com.alevel.java.nix.geronimo.entities.City;
import com.alevel.java.nix.geronimo.entities.request.SaveCity;
import com.alevel.java.nix.geronimo.service.CityCRUD;
import org.junit.jupiter.api.AfterEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeronimoCityEntityTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private CityCRUD cityCRUD;

    @AfterEach
    void end() {
        cityCRUD.deleteAll();
    }

    @Test
    void testCreateCity() {
        String name = "Chicago";

        var cityResponseEntity = createCity(name);
        assertNotNull(cityResponseEntity);

        assertEquals(HttpStatus.CREATED, cityResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, cityResponseEntity.getHeaders().getContentType());

        City responseBody = cityResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetCityById() {
        String name = "Kiev";

        var city = createCity(name).getBody();
        assertNotNull(city);

        Long id = city.getId();

        var cityUrl = "http://localhost:" + port + "/cities/" + id;

        ResponseEntity<City> cityResponseEntity = rest.getForEntity(cityUrl, City.class);
        assertEquals(HttpStatus.OK, cityResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, cityResponseEntity.getHeaders().getContentType());

        City responseBody = cityResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetNonExistingCity() {
        final int nonExistingId = 999;
        var cityUrl = "http://localhost:" + port + "/cities/" + nonExistingId;
        ResponseEntity<City> cityResponseEntity = rest.getForEntity(cityUrl, City.class);
        assertEquals(HttpStatus.NOT_FOUND, cityResponseEntity.getStatusCode());
    }

    @Test
    void testUpdateCity() {
        String name = "New York";

        var city = createCity(name).getBody();
        assertNotNull(city);

        Long id = city.getId();

        var cityUrl = "http://localhost:" + port + "/cities/" + id;

        String updatedName = "Voronezh";

        var request = new SaveCity();
        request.setName(updatedName);
        rest.put(cityUrl, request);

        ResponseEntity<City> cityResponseEntity = rest.getForEntity(cityUrl, City.class);
        assertEquals(HttpStatus.OK, cityResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, cityResponseEntity.getHeaders().getContentType());

        City responseBody = cityResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(updatedName, responseBody.getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testDeleteCity() {
        String name = "France";

        var city = createCity(name).getBody();
        assertNotNull(city);

        Long id = city.getId();

        var cityUrl = "http://localhost:" + port + "/cities/" + id;
        var cityUri = URI.create(cityUrl);

        ResponseEntity<City> cityResponseEntity = rest
                .exchange(RequestEntity.delete(cityUri).build(), City.class);
        assertEquals(HttpStatus.OK, cityResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, cityResponseEntity.getHeaders().getContentType());

        City responseBody = cityResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertNotNull(responseBody.getId());

        assertEquals(HttpStatus.NOT_FOUND, rest
                .getForEntity(cityUrl, City.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(cityUri).build(), City.class)
                .getStatusCode());
    }

    private ResponseEntity<City> createCity(String name) {
        String url = "http://localhost:" + port + "/cities";

        var requestCity = new SaveCity();
        requestCity.setName(name);

        return rest.postForEntity(url, requestCity, City.class);
    }
}
