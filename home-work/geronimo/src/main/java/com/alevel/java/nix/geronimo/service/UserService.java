package com.alevel.java.nix.geronimo.service;

import com.alevel.java.nix.geronimo.controller.PasswordController;
import com.alevel.java.nix.geronimo.entities.User;
import com.alevel.java.nix.geronimo.entities.request.SaveUser;
import com.alevel.java.nix.geronimo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new PasswordController();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return userRepository.findByUsername(s);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User create(SaveUser request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        user.setActive(true);
        return userRepository.save(user);
    }
}
