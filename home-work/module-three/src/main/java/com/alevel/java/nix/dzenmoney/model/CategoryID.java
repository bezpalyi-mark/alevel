package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CategoryID implements Serializable {

    private Long id;

    @Column(nullable = false)
    private String name;

    public CategoryID() {
    }

    public CategoryID(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
