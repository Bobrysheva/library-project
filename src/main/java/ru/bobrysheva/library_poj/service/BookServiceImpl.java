package ru.bobrysheva.library_poj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.entity.Book;
import ru.bobrysheva.library_poj.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
//                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}