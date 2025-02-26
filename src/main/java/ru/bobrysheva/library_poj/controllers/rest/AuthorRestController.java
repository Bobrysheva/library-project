package ru.bobrysheva.library_poj.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
import ru.bobrysheva.library_poj.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping("/authors/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/authors/name-1/{name}")
    List<AuthorDto> findAuthorsByName1(@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV1(name);
    }

    @GetMapping("/authors/name-2/{name}")
    List<AuthorDto> findAuthorsByName2(@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV2(name);
    }

    @GetMapping("/authors/name-3/{surname}")
    List<AuthorDto> findAuthorsByName3(@PathVariable("surname") String surname) {
        return authorService.findAuthorsBySurnameV3(surname);
    }

    @GetMapping("/authors/name-3/{surname}")
    AuthorDto findAuthorsByName3(@PathVariable("surname") String surname) {
        return authorService.getAuthorsBySurnameV3(surname);
    }

    @GetMapping("/authors")
    List<AuthorDto> findByBooks_Id(@RequestParam Long id) {
        return authorService.findByBooks_Id(id);
    }

    @PostMapping("/authors/create")
    AuthorDto createAuthor(@RequestBody AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("authors/update")
    AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }
    @DeleteMapping("/authors/delete/{id}")

    void deleteAuthor (@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}