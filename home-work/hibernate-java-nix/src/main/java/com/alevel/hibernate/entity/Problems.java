package com.alevel.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "problems")
public class Problems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_city")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city")
    private City toCity;

    public Problems() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
