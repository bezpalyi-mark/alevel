package com.alevel.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ConnectionId implements Serializable {

    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "from_city")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city")
    private City toCity;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }
}
