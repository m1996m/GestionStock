package com.GestionStock.stock.controller;

import com.GestionStock.stock.dto.user.UserDto;
import com.GestionStock.stock.dto.user.UserMapper;
import com.GestionStock.stock.dto.user.UserResponseDto;
import com.GestionStock.stock.model.Users;
import com.GestionStock.stock.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Udapte user by slug")
    @PutMapping("/update/{slug}")
    public UserDto updateUsersBySlug(@PathVariable String slug, @RequestBody UserDto userDto){
        Users user = userService.updateUser(slug, userDto);

        return userMapper.toDto(user);
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body) {
        userService.sendEmail(to, subject, body);
        return "Email envoyé avec succès à " + to;
    }

    @Operation(summary = "Get all User")
    @GetMapping("/list")
    public List<UserResponseDto> getListUser(){
        List<Users> users = userService.findAllUser();

        return users.stream().map(userMapper::toResponseDto).toList();
    }

    @Operation(summary = "Get all User by slugEntreprise")
    @GetMapping("/list/entreprise")
    public List<UserResponseDto> getListUserByCompany(@RequestParam("slugEntreprise") String slugEntreprise){
        List<Users> users = userService.findAllUserByCompany(slugEntreprise);

        return users.stream().map(userMapper::toResponseDto).toList();
    }

    @Operation(summary = "Get User By Slug")
    @GetMapping("/{slug}")
    public UserResponseDto getUserBySlug(@PathVariable String slug){
        Users user = userService.findUserBySlug(slug);

        return userMapper.toResponseDto(user);
    }

    @Operation(summary = "Get User By email or tel")
    @GetMapping("/search")
    public UserResponseDto searchUserByEmail(@RequestParam("value") String value){
        Users user = userService.findUserByEmailOrTel(value);

        return userMapper.toResponseDto(user);
    }

    @Operation(summary = "Upadate role User By Slug")
    @PutMapping("/update/role/{slug}")
    public UserResponseDto UpdateRoleUserBySlug(@PathVariable String slug, @RequestBody UserDto userDto){
        Users user = userService.updateUserRole(slug,userDto);

        return userMapper.toResponseDto(user);
    }

    @Operation(summary = "Delete User By Slug")
    @DeleteMapping("/delete/{slug}")
    public String deleteUserBySlug(@PathVariable String slug){

        return userService.deleteUserBySlug(slug);
    }

}
