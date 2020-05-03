package com.alevel.hibernate.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "connection")
public class Connection implements Serializable {

    @EmbeddedId
    private final ConnectionId id;

    public Connection() {
        id = new ConnectionId();
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
}


