package com.GestionStock.stock.ListEnumeration;

import lombok.Getter;

@Getter
public enum Genre {
    HOMME("Homme"),
    FEMME("Femme"),
    AUTRE("Non precisé");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

}

