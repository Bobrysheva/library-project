package ru.bobrysheva.library_poj.service;

import ru.bobrysheva.library_poj.dto.BookDto;

public interface BookService {
    BookDto getByNameV1(String name);
    BookDto getByNameV2(String name);
}