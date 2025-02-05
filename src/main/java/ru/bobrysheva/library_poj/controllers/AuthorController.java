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
    @GetMapping ("/authors/by-name/{name}")
    List <AuthorDto> findAuthorsByName(@PathVariable("name") String name) {
        return authorService.findAuthorsByName(name);
    }
    @GetMapping ("/authors")
    List <AuthorDto> findByBooks_Id (@RequestParam Long id) {
        return authorService.findByBooks_Id(id);
    }
}
