package com.GestionStock.stock.authJwt.controller;


import com.GestionStock.stock.authJwt.LoginResponse;
import com.GestionStock.stock.authJwt.service.AuthenticationService;
import com.GestionStock.stock.authJwt.service.JwtService;
import com.GestionStock.stock.dto.auth.LoginUserDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.service.EntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, EntrepriseService entrepriseService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody UserDto userDto) {
        Users registeredUser = authenticationService.signup(userDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Users authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Upadate to password by slug")
    @PostMapping("/update/{slug}")
    public String updatePasswordBySlug(@RequestBody UserDto userDto, @PathVariable String slug) {
        return authenticationService.updatePassword(userDto, slug);
    }

    @Operation(summary = "Verify same password ")
    @PostMapping("/{email}")
    public boolean verifySamePassword(@PathVariable String email) {
        return authenticationService.verifySamePassword(email);
    }
}
