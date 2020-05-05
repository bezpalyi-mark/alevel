package com.alevel.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_city")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city")
    private City toCity;

    public Problem() {
    }

    public Problem(Long id, City fromCity, City toCity) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Problem)) return false;
        Problem problem = (Problem) o;
        return getId().equals(problem.getId()) &&
                getFromCity().equals(problem.getFromCity()) &&
                getToCity().equals(problem.getToCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFromCity(), getToCity());
    }
}
