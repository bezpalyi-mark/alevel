package com.alevel.java.nix.dzenmoney.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ExportOrder {

    private final Long id;

    private final String instance;

    private final BigDecimal value;

    private String category;

    private ExportOrder(Long id, String instance, BigDecimal value, String category) {
        this.id = id;
        this.instance = instance;
        this.value = value;
        this.category = category;
    }

    public static ExportOrder of(Long id, String instance, BigDecimal value, String category) {
        return new ExportOrder(id, instance, value, category);
    }

    public Long getId() {
        return id;
    }

    public String getInstance() {
        return instance;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public void addCategory(String category) {
        this.category = this.category + "|" + category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExportOrder)) return false;
        ExportOrder that = (ExportOrder) o;
        return getId().equals(that.getId()) &&
                getInstance().equals(that.getInstance()) &&
                getValue().equals(that.getValue()) &&
                getCategory().equals(that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInstance(), getValue(), getCategory());
    }
}
