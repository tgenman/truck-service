package com.mpoznyak.model.type;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

public enum CargoStatus {

    PREPARED("Prepared"), PICKED_UP("Picked Up"), DROPPED_OFF("Dropped Off");

    private String name;

    CargoStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
