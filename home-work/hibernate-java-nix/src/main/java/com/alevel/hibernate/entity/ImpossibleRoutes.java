package com.alevel.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "impossible_routes")
public class ImpossibleRoutes {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private final RouteId problemId;

    @OneToOne
    @JoinColumn(name = "problem_id")
    @MapsId("prId")
    private Problems problem;

    public ImpossibleRoutes() {
        problemId = new RouteId();
    }


    public Problems getProblem() {
        return problem;
    }

    public void setProblem(Problems problem) {
        this.problem = problem;
    }
}
