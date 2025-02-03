package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.AuthorDto;

public interface AuthorService {

   AuthorDto getAuthorById (Long id);
}
