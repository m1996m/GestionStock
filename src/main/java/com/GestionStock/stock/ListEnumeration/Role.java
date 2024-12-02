package com.GestionStock.stock.ListEnumeration;

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

