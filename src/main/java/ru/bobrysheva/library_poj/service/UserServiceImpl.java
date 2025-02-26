package ru.bobrysheva.library_poj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.UserDto;
import ru.bobrysheva.library_poj.model.User;
import ru.bobrysheva.library_poj.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addNewUser(User user) {
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        User newUser = userRepository.save(user);
        return convertUserToDto(newUser);
    }

    @Override
    public List <UserDto> getAllUsers () {
        return userRepository.findAll().stream().map(this::convertUserToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUserDto(User user) {
        User newUser = userRepository.findUserById(user.getId());
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(passwordEncode);
        newUser.setRole(user.getRole());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        userRepository.save(newUser);
        return convertUserToDto(newUser);
    }

    private UserDto convertUserToDto (User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
