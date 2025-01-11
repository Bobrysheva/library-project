package ru.bobrysheva.library_project.service;


import ru.bobrysheva.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
}
