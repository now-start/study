package org.nowstart.study.data.type;

import lombok.Getter;

@Getter
public enum RolesType {

    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    RolesType(String role) {
        this.role = role;
    }
}
