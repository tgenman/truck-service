package com.mpoznyak.model;

import javax.persistence.*;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Entity
public class Road {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "city_id1")
    private City city1;

    @OneToOne
    @JoinColumn(name = "city_id2")
    private City city2;

    @Column(name = "distance")
    private Long distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }
}
