package com.alevel.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "found_routes")
public class FoundRoutes {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private final RouteId problemId;

    @Column(name = "cost")
    private Integer minCost;

    @OneToOne
    @JoinColumn(name = "problem_id")
    @MapsId("prId")
    private Problems problem;

    public FoundRoutes() {
        problemId = new RouteId();
    }

    public Integer getMinCost() {
        return minCost;
    }

    public void setMinCost(Integer minCost) {
        this.minCost = minCost;
    }

    public Problems getProblem() {
        return problem;
    }

    public void setProblem(Problems problemFk) {
        this.problem = problemFk;
    }
}
