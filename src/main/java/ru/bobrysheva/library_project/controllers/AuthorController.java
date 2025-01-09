package ru.bobrysheva.library_project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.bobrysheva.library_project.dto.AuthorDto;
import ru.bobrysheva.library_project.service.AuthorService;
import ru.bobrysheva.library_project.service.AuthorServiceImpl;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImpl authorServiceImpl;


    @GetMapping ("/author/{id}")
    AuthorDto getAuthorById (@PathVariable ("id") Long id) {
        return authorServiceImpl.getAuthorById(id);
    }
}
