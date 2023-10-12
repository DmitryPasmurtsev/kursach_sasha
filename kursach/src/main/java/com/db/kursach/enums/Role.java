package com.db.kursach.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_DIRECTOR("Директор"),
    ROLE_FOREMAN("Бригадир");
    public String name;
    Role (String name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return name();
    }
}
