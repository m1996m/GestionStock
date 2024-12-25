package com.GestionStock.stock.listEnumeration;

import lombok.Getter;

@Getter
public enum Role {
    USER("User"),
    ADMIN("Admin"),
    MANAGER("Manager");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

}

