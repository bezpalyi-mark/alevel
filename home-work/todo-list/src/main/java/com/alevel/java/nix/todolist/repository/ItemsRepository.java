package com.alevel.java.nix.todolist.repository;

import com.alevel.java.nix.todolist.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Long> {
    List<Item> findByDoneFalse();
}
