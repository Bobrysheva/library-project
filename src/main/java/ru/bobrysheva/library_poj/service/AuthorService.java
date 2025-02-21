package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
import ru.bobrysheva.library_poj.model.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorService {

    AuthorDto getAuthorById(Long id);

    List<AuthorDto> getAuthorsByNameV1(String name);

    List<AuthorDto> getAuthorsByNameV2(String name);

    AuthorDto getAuthorsBySurnameV3(String surname);

    Author getAuthorsBySurnameV4(String surname);

    List<AuthorDto> findByBooksId(Long bookId);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);

    Author convertAuthorDtoToEntity(AuthorDto authorDto);
}