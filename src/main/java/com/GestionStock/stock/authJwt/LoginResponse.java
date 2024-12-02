package com.GestionStock.stock.authJwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    public String token;
    public long expiresIn;
}
