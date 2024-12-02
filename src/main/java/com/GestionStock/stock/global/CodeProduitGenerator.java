package com.GestionStock.stock.global;

import java.security.SecureRandom;

public class CodeProduitGenerator {

    public static String generateCombination(String name, String type) {
        if (name == null || type == null) {
            throw new IllegalArgumentException("Le nom et le type du produit ne peuvent pas être null.");
        }

        String namePart = name.length() >= 3 ? name.substring(0, 3).toUpperCase() : name.toUpperCase();
        String typePart = type.length() >= 2 ? type.substring(0,2).toUpperCase() : type.toUpperCase();

        return namePart + "" + typePart;
    }
}

