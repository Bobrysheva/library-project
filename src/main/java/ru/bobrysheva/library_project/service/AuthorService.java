package ru.bobrysheva.library_project.service;

import lombok.RequiredArgsConstructor;
import ru.bobrysheva.library_project.dto.AuthorDto;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);
}
