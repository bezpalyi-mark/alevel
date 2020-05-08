package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public abstract class Category {

    @EmbeddedId
    private CategoryID categoryID;

    public CategoryID getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(CategoryID categoryID) {
        this.categoryID = categoryID;
    }
}
