package ru.bobrysheva.library_poj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.bobrysheva.library_poj.dto.GenreDto;
import ru.bobrysheva.library_poj.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genres/{id}")
    GenreDto getGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id);
    }
}
