package com.alevel.java.nix.dzenmoney.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne
    private User user;

    public Account() {
    }

    public Account(Long id, BigDecimal balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getId().equals(account.getId()) &&
                getBalance().equals(account.getBalance()) &&
                getUser().equals(account.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBalance(), getUser());
    }
}
