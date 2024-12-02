package com.GestionStock.stock.service;

import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntrepriseService entrepriseService;
    private final JavaMailSender mailSender;

    public Users updateUser(String slug, UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findBySlug(slug);

        if(optionalUsers.isEmpty()){
            throw new RuntimeException("User non trouvée");
        }

        try {
            Users usersToUpdate = userDto.update(userDto, optionalUsers.get());
            return userRepository.save(usersToUpdate);
        }catch (Exception e){
            throw new RuntimeException("Une erreur lors de la modification");
        }
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("mmd1996.m@gmail.com");

        mailSender.send(message);
    }

    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    public List<Users> findAllUserByCompany(String slugEntreprise) {
        Entreprise optionalEntreprise = entrepriseService.findEntrepriseBySlug(slugEntreprise);

        List<Users> users = userRepository.findByEntreprise(optionalEntreprise);

        return users.stream()
                .sorted(Comparator.comparing(Users::getFirstName))
                .collect(Collectors.toList());
    }

    public Users findUserBySlug(String slug) {
        Optional<Users> optionalUser = userRepository.findBySlug(slug);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User non trouvée");
        }

        return optionalUser.get();
    }

    public Users findUserById(Long id) {
        Optional<Users> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User non trouvée");
        }

        return optionalUser.get();
    }

    public Users findUserByEmailOrTel(String value) {
        Optional<Users> optionalUser = userRepository.findByEmailOrTel(value);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User non trouvée");
        }

        return optionalUser.get();
    }

    public String deleteUserBySlug(String slug) {
        Optional<Users> optionalUser = userRepository.findBySlug(slug);

        if (optionalUser.isEmpty()){
            throw new RuntimeException("User non trouvé");
        }

        try {
            userRepository.delete(optionalUser.get());
        }catch (Exception e){
            throw new RuntimeException("Une erreur lors de la suppression",e);
        }

        return "Suppression réussie";
    }

    public Users updateUserRole(String slug, UserDto userDto) {
        Optional<Users> optionalUsers = userRepository.findBySlug(slug);

        if(optionalUsers.isEmpty()){
            throw new RuntimeException("User non trouvée");
        }else {
            try {
                Users usersToUpdateRole = userDto.updateRole(userDto, optionalUsers.get());
                return userRepository.save(usersToUpdateRole);
            }catch (Exception e){
                throw new RuntimeException("Une erreur lors de la modification");
            }
        }
    }
}
