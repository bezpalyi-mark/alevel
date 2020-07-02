package com.alevel.java.nix.geronimo;

import com.alevel.java.nix.geronimo.entities.Category;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;
import com.alevel.java.nix.geronimo.service.CategoryCRUD;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeronimoCategoryEntityTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private CategoryCRUD categoryCRUD;

    @AfterEach
    void end() {
        categoryCRUD.deleteAll();
    }

    @Test
    void testContextLoads() {
        assertNotNull(rest);
        assertNotEquals(0, port);
    }

    @Test
    void testCreateCategory() {
        String name = "entertainment";

        var categoryResponseEntity = createCategory(name);
        assertNotNull(categoryResponseEntity);

        assertEquals(HttpStatus.CREATED, categoryResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, categoryResponseEntity.getHeaders().getContentType());

        Category responseBody = categoryResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertNotNull(responseBody.getId());
    }

    @Test
    void testGetCategoryById() {
        String name = "job";

        var category = createCategory(name).getBody();
        assertNotNull(category);

        Long id = category.getId();

        var categoryUrl = "http://localhost:" + port + "/categories/" + id;

        ResponseEntity<Category> categoryResponseEntity = rest.getForEntity(categoryUrl, Category.class);
        assertEquals(HttpStatus.OK, categoryResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, categoryResponseEntity.getHeaders().getContentType());

        Category responseBody = categoryResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertEquals(id, responseBody.getId());
    }

    @Test
    void testGetNonExistingCategory() {
        final int nonExistingId = 999;
        var categoryUrl = "http://localhost:" + port + "/categories/" + nonExistingId;
        ResponseEntity<Category> categoryResponseEntity = rest.getForEntity(categoryUrl, Category.class);
        assertEquals(HttpStatus.NOT_FOUND, categoryResponseEntity.getStatusCode());
    }

    @Test
    void testUpdateCategory() {
        String name = "before";

        var category = createCategory(name).getBody();
        assertNotNull(category);

        Long id = category.getId();

        var categoryUrl = "http://localhost:" + port + "/categories/" + id;

        String updatedName = "after";

        var request = new SaveCategory();
        request.setName(updatedName);
        rest.put(categoryUrl, request);

        ResponseEntity<Category> categoryResponseEntity = rest.getForEntity(categoryUrl, Category.class);
        assertEquals(HttpStatus.OK, categoryResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, categoryResponseEntity.getHeaders().getContentType());

        Category responseBody = categoryResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(updatedName, responseBody.getName());
        assertEquals(id, responseBody.getId());
    }

    @Test
    void testDeleteCategory() {
        String name = "entertainment";

        var category = createCategory(name).getBody();
        assertNotNull(category);

        Long id = category.getId();

        var categoryUrl = "http://localhost:" + port + "/categories/" + id;
        var categoryUri = URI.create(categoryUrl);

        ResponseEntity<Category> categoryResponseEntity = rest
                .exchange(RequestEntity.delete(categoryUri).build(), Category.class);
        assertEquals(HttpStatus.OK, categoryResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, categoryResponseEntity.getHeaders().getContentType());

        Category responseBody = categoryResponseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(name, responseBody.getName());
        assertEquals(id, responseBody.getId());

        assertEquals(HttpStatus.NOT_FOUND, rest
                .getForEntity(categoryUrl, Category.class).getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, rest
                .exchange(RequestEntity.delete(categoryUri).build(), Category.class)
                .getStatusCode());
    }

    private ResponseEntity<Category> createCategory(String name) {
        String url = "http://localhost:" + port + "/categories";

        var requestCategory = new SaveCategory();
        requestCategory.setName(name);

        return rest.postForEntity(url, requestCategory, Category.class);
    }
}
