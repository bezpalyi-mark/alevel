package com.alevel.java.nix.geronimo.entities.request;

import com.alevel.java.nix.geronimo.entities.Role;

import javax.persistence.*;
import java.util.Set;

public class SaveUser {
    private String username;

    private String password;

    private Boolean active;

    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
