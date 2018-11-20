package com.mpoznyak.model.type;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

public enum DriverStatus {

    WEEKLY_REST("Weekly rest"), SHIFT_REST("Rest during shift"), SECOND_DRIVER("Second driver (partner)"),
    DRIVER("Driver"), CARGO_OPERATIONS("Cargoes operations"), FREE("Free for a new order");

    private String name;

    DriverStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
