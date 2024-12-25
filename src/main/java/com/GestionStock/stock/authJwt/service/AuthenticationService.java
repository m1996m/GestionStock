package com.GestionStock.stock.authJwt.service;


import com.GestionStock.stock.dto.auth.LoginUserDto;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.repository.UserRepository;
import com.GestionStock.stock.service.EntrepriseService;
import com.GestionStock.stock.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final EntrepriseService entrepriseService;
    private final UserService userService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, EntrepriseService entrepriseService, UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.entrepriseService = entrepriseService;
        this.userService = userService;
    }

    public Users signup(UserDto userDto) {
        Entreprise entreprise = entrepriseService.findById(userDto.getIdEntreprise());
        Users user = userDto.create(userDto, entreprise);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public String updatePassword(UserDto userDto, String slug) {
        Users user = userService.findUserBySlug(slug);

        Users userToUpadatePassword = userDto.updatePassword(userDto,user);

        userToUpadatePassword.setPassword(passwordEncoder.encode(userToUpadatePassword.getPassword()));

        try {
            userRepository.save(userToUpadatePassword);
        }catch (Exception e){
            throw new RuntimeException("Une erreur lors de la mise à jour du mot de passe");
        }

        return "Le mot de passe à été bien modifié";
    }

    public boolean verifySamePassword(String email) {
        Optional<Users> optionalUsers = userRepository.findByEmail(email);

        if (optionalUsers.isEmpty())
            return false;

        Users user = optionalUsers.get();

        return !Objects.equals(user.getPassword(), passwordEncoder.encode(email));
    }

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}

