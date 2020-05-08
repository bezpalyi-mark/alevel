package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    private BigDecimal value;

    @Column(nullable = false, columnDefinition = "Timestamp")
    private Instant instant;

    public Operation() {
    }

    public Operation(Long id, Account account, List<Category> categories, BigDecimal value, Instant instant) {
        this.id = id;
        this.account = account;
        this.categories = categories;
        this.value = value;
        this.instant = instant;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal transactionValue) {
        this.value = transactionValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return getId().equals(operation.getId()) &&
                getAccount().equals(operation.getAccount()) &&
                getCategories().equals(operation.getCategories()) &&
                getValue().equals(operation.getValue()) &&
                getInstant().equals(operation.getInstant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccount(), getCategories(), getValue(), getInstant());
    }

    public static class Builder {
        private final Operation operation;

        public Builder() {
            operation = new Operation();
        }

        public Builder setId(Long id) {
            operation.setId(id);
            return this;
        }

        public Builder setAccount(Account account) {
            operation.setAccount(account);
            return this;
        }

        public Builder addCategory(Category category) {
            operation.addCategory(category);
            return this;
        }

        public Builder setTransactionValue(BigDecimal transactionValue) {
            operation.setValue(transactionValue);
            return this;
        }

        public Builder setInstant(Instant instant) {
            operation.setInstant(instant);
            return this;
        }

        public Operation build() {
            return operation;
        }

    }
}
