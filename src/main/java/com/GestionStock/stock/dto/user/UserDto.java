package com.GestionStock.stock.dto.user;

import com.GestionStock.stock.ListEnumeration.Genre;
import com.GestionStock.stock.ListEnumeration.Role;
import com.GestionStock.stock.dto.entreprise.EntrepriseDto;
import com.GestionStock.stock.dto.magasin.MagasinDto;
import com.GestionStock.stock.dto.magasin.MagasinMapper;
import com.GestionStock.stock.dto.vente.VenteDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private long idUser;
    private String email;
    private String firstName;
    private String tel;
    private String address;
    private String lastName;
    private String password;
    private String slug;
    private Role role = Role.USER;
    private String avatar;
    private Genre gender = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idEntreprise;
    private long idMagasin;

    public Users create(UserDto userDto, Entreprise entreprise){
        Users user = new Users();

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEntreprise(entreprise);

        return user;
    }

    public UserDto toDto(Users users){
        return UserDto.builder()
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .address(users.getAddress())
                .avatar(users.getAvatar())
                .idUser(users.getIdUser())
                .gender(users.getGender())
                .role(users.getRole())
                .createdAt(users.getCreatedAt())
                .build();
    }

    public Users login(UserDto userDto){
        PasswordEncoder passwordEncoder = null;
        
        Users user = new Users();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }

    public Users update(UserDto userDto, Users user){
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setTel(userDto.getTel());
        user.setGender(userDto.getGender());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users users = (Users) principal;
        user.setMagasin(users.getMagasin());

        return user;
    }

    public Users updateRole(UserDto userDto, Users user){
        user.setRole(userDto.getRole());

        return user;
    }

    public Users enableUnableStatus(Users user){
        //user.setRole(user.());

        return user;
    }

    public Users updatePassword(UserDto userDto, Users user){
        user.setPassword(userDto.getPassword());

        return user;
    }
}
