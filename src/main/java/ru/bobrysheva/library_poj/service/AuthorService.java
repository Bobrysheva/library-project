package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

   AuthorDto getAuthorById (Long id);

   List<AuthorDto> findAuthorsByNameV1(String name);
   List<AuthorDto> findAuthorsByNameV2(String name);
   List<AuthorDto> findAuthorsBySurnameV3(String surname);

   List<AuthorDto> findByBooks_Id (Long bookId);
}
