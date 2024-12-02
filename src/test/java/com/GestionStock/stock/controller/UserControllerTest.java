package com.GestionStock.stock.controller;

import com.GestionStock.stock.authJwt.service.JwtService;
import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.service.EntrepriseService;
import com.GestionStock.stock.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EntrepriseService entrepriseService;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void updateUsersBySlug() throws Exception {
        Users users = new Users();
        users.setAddress("Cité");
        users.setEmail("st@gmail.com");
        users.setTel("0098");
        users.setFirstName("Diallo");
        users.setLastName("Malick");
        users.setSlug("slug");
        users.setIdUser(1L);

        UserDto userDto = new UserDto();
        userDto.setAddress("Cité");
        userDto.setEmail("st@gmail.com");
        userDto.setTel("0098");
        userDto.setFirstName("Oumarou");
        userDto.setLastName("diallo");
        userDto.setSlug("slug");
        userDto.setIdUser(1L);


        when(userService.updateUser(users.getSlug(), userDto)).thenReturn(users);

        mockMvc.perform(put("/api/user/update/{slug}", users.getSlug()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()));
    }

    @Test
    void getListUser() {
    }

    @Test
    void getListUserByCompany() {
    }

    @Test
    void getUserBySlug() {
    }

    @Test
    void searchUserByEmail() {
    }

    @Test
    void updateRoleUserBySlug() {
    }

    @Test
    void deleteUserBySlug() {
    }
}