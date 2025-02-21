package ru.bobrysheva.library_poj.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bobrysheva.library_poj.dto.UserDto;
import ru.bobrysheva.library_poj.model.User;
import ru.bobrysheva.library_poj.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity <UserDto> addNewUser (@RequestBody User user){
        return ResponseEntity.ok(userService.addNewUser(user));
    }

    @GetMapping ("/users")
    List <UserDto> getAllUsers () {
        return userService.getAllUsers();
    }

    @PutMapping ("/users")
    public UserDto updateUser (@RequestBody User user) {
        return userService.updateUserDto(user);
    }
}
