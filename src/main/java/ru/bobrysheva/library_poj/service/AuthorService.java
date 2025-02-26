package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
import ru.bobrysheva.library_poj.model.Author;

import java.util.List;

public interface AuthorService {

   AuthorDto getAuthorById (Long id);

   List<AuthorDto> findAuthorsByNameV1(String name);
   List<AuthorDto> findAuthorsByNameV2(String name);
   List<AuthorDto> findAuthorsBySurnameV3(String surname);
   List<Author> findAuthorsBySurname(String surname);
   List<AuthorDto> findByBooksId (Long bookId);
   AuthorDto createAuthor(AuthorCreateDto authorCreateDto);
   AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);
   void deleteAuthor (Long id);
   AuthorDto convertEntityToDto (Author author);
}
