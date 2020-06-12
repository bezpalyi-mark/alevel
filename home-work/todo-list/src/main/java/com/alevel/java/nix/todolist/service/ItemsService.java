package com.alevel.java.nix.todolist.service;

import com.alevel.java.nix.todolist.entity.Item;
import com.alevel.java.nix.todolist.entity.request.SaveItem;
import com.alevel.java.nix.todolist.exception.ItemNotFoundException;
import com.alevel.java.nix.todolist.repository.ItemsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemsService implements ItemsCRUD {

    private final ItemsRepository itemRepository;

    public ItemsService(ItemsRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> getAllUndone() {
        return itemRepository.findByDoneFalse();
    }

    @Override
    public Item create(SaveItem request) {
        Item item = new Item();
        item.setText(request.getText());
        item.setDone(request.isDone());
        return itemRepository.save(item);
    }

    @Override
    public void update(Long id, SaveItem request) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        item.setText(request.getText());
        item.setDone(request.isDone());
        itemRepository.save(item);
    }

    @Override
    public Optional<Item> deleteById(Long id) {
        Optional<Item> listItem = itemRepository.findById(id);
        listItem.ifPresent(itemRepository::delete);
        return listItem;
    }
}
