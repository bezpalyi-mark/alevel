package com.alevel.java.nix.todolist.service;

import com.alevel.java.nix.todolist.entity.ListItem;
import com.alevel.java.nix.todolist.entity.request.SaveListItem;
import com.alevel.java.nix.todolist.exception.ListItemNotFoundException;
import com.alevel.java.nix.todolist.repository.ListItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ListItemService implements ListItemCRUD {

    private final ListItemRepository itemRepository;

    public ListItemService(ListItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ListItem> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<ListItem> getById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<ListItem> getAllUndone() {
        return itemRepository.findByDoneFalse();
    }

    @Override
    public ListItem create(SaveListItem request) {
        ListItem listItem = new ListItem();
        listItem.setText(request.getText());
        listItem.setDone(request.isDone());
        return itemRepository.save(listItem);
    }

    @Override
    public void update(Long id, SaveListItem request) {
        ListItem listItem = itemRepository.findById(id).orElseThrow(() -> new ListItemNotFoundException(id));
        listItem.setText(request.getText());
        listItem.setDone(request.isDone());
        itemRepository.save(listItem);
    }

    @Override
    public Optional<ListItem> deleteById(Long id) {
        Optional<ListItem> listItem = itemRepository.findById(id);
        listItem.ifPresent(itemRepository::delete);
        return listItem;
    }
}
