package ru.bobrysheva.library_poj.service;

import ru.bobrysheva.library_poj.dto.BookCreateDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto getByNameV1(String name);
    BookDto getByNameV2(String name);
    BookDto getByNameV3(String name);
    BookDto createBookDto(BookCreateDto bookCreateDto);
    BookDto updateBookDto (BookUpdateDto bookUpdateDto);
    void deleteBook (Long id);
    List <BookDto> getAllBooks ();
}