package com.alevel.java.nix.todolist.repository;

import com.alevel.java.nix.todolist.entity.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    List<ListItem> findByDoneFalse();
}
