package com.alevel.java.nix.geronimo.repository;

import com.alevel.java.nix.geronimo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
