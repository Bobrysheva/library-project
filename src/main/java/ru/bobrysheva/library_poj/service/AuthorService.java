package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.entity.Author;

import java.util.List;

public interface AuthorService {

   AuthorDto getAuthorById (Long id);

   List<AuthorDto> findAuthorsByName (String name);

   List<AuthorDto> findByBooks_Id (Long bookId);
}
