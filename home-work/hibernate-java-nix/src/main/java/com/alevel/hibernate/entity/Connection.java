package com.alevel.hibernate.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "connection")
public class Connection implements Serializable {

    @EmbeddedId
    private final ConnectionId id;

    public Connection() {
        id = new ConnectionId();
    }

    public Connection(ConnectionId id) {
        this.id = id;
    }

    public Integer getCost() {
        return id.getCost();
    }

    public void setCost(Integer cost) {
        this.id.setCost(cost);
    }

    public City getFromCity() {
        return id.getFromCity();
    }

    public void setFromCity(City fromCity) {
        this.id.setFromCity(fromCity);
    }

    public City getToCity() {
        return id.getToCity();
    }

    public void setToCity(City toCity) {
        this.id.setToCity(toCity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection)) return false;
        Connection that = (Connection) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


