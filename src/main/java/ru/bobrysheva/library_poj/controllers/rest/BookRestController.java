
package ru.bobrysheva.library_poj.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bobrysheva.library_poj.dto.BookCreateDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.BookUpdateDto;
import ru.bobrysheva.library_poj.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/books/v1")
    BookDto getBookByName(@RequestParam("name") String name) {
        return bookService.getByNameV1(name);
    }

    @GetMapping("/books/v2")
    BookDto getBookByNameV2(@RequestParam("name") String name) {
        return bookService.getByNameV2(name);
    }

    @GetMapping("/books/v3")
    BookDto getBookByNameV3(@RequestParam("name") String name) {
        return bookService.getByNameV3(name);
    }

    @PostMapping("/books/create")
    BookDto createBook (@RequestBody BookCreateDto bookCreateDto) {
        return  bookService.createBookDto(bookCreateDto);
    }

    @PutMapping ("/books/update")
    BookDto updateBook (@RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBookDto(bookUpdateDto);
    }

    @DeleteMapping ("/books/{id}")
    void  deleteBook (@PathVariable ("id") Long id) {
        bookService.deleteBook(id);
    }
}
