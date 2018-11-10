package com.mpoznyak.model.type;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

public enum RoutePointType {
    PICK_UP("Pick Up"), DROP_OFF("Drop Off"), TRANSIT("Transit"), CAR_LOCATION("Truck Location");

    private String name;

    RoutePointType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
