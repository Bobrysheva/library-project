package ru.bobrysheva.library_poj.service;


import ru.bobrysheva.library_poj.dto.GenreDto;
import ru.bobrysheva.library_poj.model.Genre;

public interface GenreService {

    GenreDto getGenreById(Long id);

    Genre getGenreByName(String name);
}
