package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.entities.Category;
import com.alevel.java.nix.geronimo.entities.request.SaveCategory;
import com.alevel.java.nix.geronimo.exception.CategoryNotFoundException;
import com.alevel.java.nix.geronimo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements CategoryCRUD {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category create(SaveCategory request) {
        Category category = new Category();
        category.setName(request.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void update(Long id, SaveCategory request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(request.getName()));
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(categoryRepository::delete);
        return category;
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
