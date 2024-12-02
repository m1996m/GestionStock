package com.GestionStock.stock.service;

import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Entreprise;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private EntrepriseService entrepriseService;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldGetListUser(){
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");

        when(userRepository.findAll()).thenReturn(List.of(users));

        List<Users> listUsers = userService.findAllUser();

        assertEquals(users.getFirstName(), listUsers.get(0).getFirstName());

        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void shouldGetOneUserBySlug(){
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");
        users.setSlug("slug");

        when(userRepository.findBySlug(users.getSlug())).thenReturn(Optional.of(users));

        Users userToGet = userService.findUserBySlug(users.getSlug());

        assertEquals(users.getFirstName(), userToGet.getFirstName());

        verify(userRepository, times(1)).findBySlug(users.getSlug());
    }

    @Test
    public void shouldGetOneUserByEmailOrTel(){
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");
        users.setSlug("slug");

        when(userRepository.findByEmailOrTel(users.getEmail())).thenReturn(Optional.of(users));

        Users userToGet = userService.findUserByEmailOrTel(users.getEmail());

        assertEquals(users.getFirstName(), userToGet.getFirstName());

        verify(userRepository, times(1)).findByEmailOrTel(users.getEmail());
    }

    @Test
    public void shouldGetOneUserByEntreprise(){
        Entreprise entreprise = new Entreprise();
        entreprise.setIdEntreprise(1L);
        entreprise.setSlug("slug");

        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");
        users.setSlug("slug");
        users.setIdUser(1L);
        users.setEntreprise(entreprise);

        when(entrepriseService.findEntrepriseBySlug(entreprise.getSlug())).thenReturn(entreprise);
        when(userRepository.findByEntreprise(users.getEntreprise())).thenReturn(List.of(users));

        List<Users> usersToGet = userService.findAllUserByCompany(entreprise.getSlug());

        assertEquals(users.getFirstName(), usersToGet.get(0).getFirstName());

        verify(userRepository, times(1)).findByEntreprise(entreprise);
        verify(entrepriseService, times(1)).findEntrepriseBySlug(entreprise.getSlug());
    }

    @Test
    public void shouldUpdateUserBySlug(){
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");
        users.setSlug("slug");
        users.setIdUser(1L);

        UserDto userDto = new UserDto();
        userDto.setAddress("Cité");
        userDto.setEmail("st@gmail.com");
        userDto.setTel("0098");
        userDto.setFirstName("Malick");
        userDto.setLastName("diallo");
        userDto.setSlug("slug");
        userDto.setIdUser(1L);

        when(userRepository.findBySlug(users.getSlug())).thenReturn(Optional.of(users));
        when(userRepository.save(userDto.update(userDto,users))).thenReturn(userDto.update(userDto,users));

        Users userToGet = userService.updateUser(users.getSlug(),userDto);

        assertEquals(users.getFirstName(), userToGet.getFirstName());

        verify(userRepository, times(1)).save(userDto.update(userDto,users));
    }

    @Test
    public void shouldUpdateUserRoleBySlug(){
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Oumarou");
        users.setLastName("diallo");
        users.setSlug("slug");
        users.setIdUser(1L);

        UserDto userDto = new UserDto();
        userDto.setAddress("Cité");
        userDto.setEmail("st@gmail.com");
        userDto.setTel("0098");
        userDto.setFirstName("Malick");
        userDto.setLastName("diallo");
        userDto.setSlug("slug");
        userDto.setIdUser(1L);

        when(userRepository.findBySlug(users.getSlug())).thenReturn(Optional.of(users));
        when(userRepository.save(userDto.updateRole(userDto,users))).thenReturn(userDto.updateRole(userDto,users));

        Users userToGet = userService.updateUserRole(users.getSlug(),userDto);

        assertEquals(users.getFirstName(), userToGet.getFirstName());

        verify(userRepository, times(1)).save(userDto.updateRole(userDto,users));
    }

    @Test
    public void shouldDeleteUser(){
        String slugUser = "slug";

        // Configurer le mock pour lancer une exception lorsqu'il est appelé
        doThrow(new RuntimeException("User non trouvé")).when(userRepository).deleteBySlug(slugUser);

        // Vérifier que l'exception est levée lorsque la méthode est appelée
        assertThrows(RuntimeException.class, () -> userRepository.deleteBySlug(slugUser));

        // Vérifier que la méthode a bien été appelée une fois
        verify(userRepository, times(1)).deleteBySlug(slugUser);
    }

}