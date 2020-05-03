package com.alevel.hibernate.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RouteId implements Serializable {
    private Long prId;

    public Long getPrId() {
        return prId;
    }

    public void setPrId(Long prId) {
        this.prId = prId;
    }
}
