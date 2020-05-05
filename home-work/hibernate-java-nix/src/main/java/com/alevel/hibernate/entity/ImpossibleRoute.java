package com.alevel.hibernate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "impossible_routes")
public class ImpossibleRoute {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Problem problem;

    public ImpossibleRoute() {
    }


    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImpossibleRoute)) return false;
        ImpossibleRoute that = (ImpossibleRoute) o;
        return getId().equals(that.getId()) &&
                getProblem().equals(that.getProblem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProblem());
    }
}
