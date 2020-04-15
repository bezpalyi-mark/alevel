package com.alevel.jdbcbox.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphConnectionsFromDatabase {
    private final List<Integer> from;
    private final List<Integer> to;
    private final List<Integer> cost;

    private int fromCounter = 0;
    private int toCounter = 0;
    private int costCounter = 0;

    private int size = 0;

    GraphConnectionsFromDatabase() {
        from = new ArrayList<>();
        to = new ArrayList<>();
        cost = new ArrayList<>();
    }

    public void addConnection(int from, int to, int cost) {
        this.from.add(from);
        this.to.add(to);
        this.cost.add(cost);
        size++;
    }

    public Integer nextFrom() {
        Integer next = from.get(fromCounter);
        fromCounter++;
        return next;
    }

    public Integer nextTo() {
        Integer next = to.get(toCounter);
        toCounter++;
        return next;
    }

    public Integer nextCost() {
        Integer next = cost.get(costCounter);
        costCounter++;
        return next;
    }

    public int size() {
        return size;
    }

    public void resetCounters() {
        costCounter = 0;
        fromCounter = 0;
        toCounter = 0;
    }
}
