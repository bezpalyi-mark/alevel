package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryID().equals(category.getCategoryID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryID());
    }
}
