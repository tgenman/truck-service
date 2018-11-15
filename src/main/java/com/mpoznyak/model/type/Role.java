package com.mpoznyak.model.type;

/**
 * Created by Max Poznyak
 * on 11/7/18  at 16:22
 */
public enum  Role {

    MANAGER("ROLE_MANAGER"), DRIVER("ROLE_DRIVER"), ADMIN("ROLE_ADMIN");

    Role(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
