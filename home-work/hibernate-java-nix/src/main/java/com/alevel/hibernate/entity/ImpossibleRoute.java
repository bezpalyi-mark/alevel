package com.alevel.hibernate.entity;

import javax.persistence.*;

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
}
