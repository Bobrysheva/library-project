package ru.bobrysheva.library_poj.service;

import ru.bobrysheva.library_poj.dto.UserDto;
import ru.bobrysheva.library_poj.model.User;

import java.util.List;

public interface UserService {
    UserDto addNewUser (User user);

    List <UserDto> getAllUsers ();

    UserDto updateUserDto (User user);
}
