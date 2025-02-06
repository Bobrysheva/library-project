package ru.bobrysheva.library_poj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authors/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }
    @GetMapping ("/authors/name-1/{name}")
    List <AuthorDto> findAuthorsByName1(@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV1(name);
    }
    @GetMapping ("/authors/name-2/{name}")
    List <AuthorDto> findAuthorsByName2 (@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV2(name);
    }
    @GetMapping ("/authors/name-3/{surname}")
    List <AuthorDto> findAuthorsByName3 (@PathVariable("surname") String surname) {
        return authorService.findAuthorsBySurnameV3(surname);
    }
    @GetMapping ("/authors")
    List <AuthorDto> findByBooks_Id (@RequestParam Long id) {
        return authorService.findByBooks_Id(id);
    }
}
