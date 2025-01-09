package ru.bobrysheva.library_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_project.dto.AuthorDto;

@Service
public interface AuthorService {
    AuthorDto getAuthorById(Long id);
}
